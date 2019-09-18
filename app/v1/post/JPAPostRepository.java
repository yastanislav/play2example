package v1.post;

import net.jodah.failsafe.CircuitBreaker;
import net.jodah.failsafe.Failsafe;
import play.db.jpa.JPAApi;
import v1.post.entity.PostData;
import v1.post.model.PostResource;
import v1.post.model.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * A repository that provides a non-blocking API with a custom execution context
 * and circuit breaker.
 */
@Singleton
public class JPAPostRepository implements PostRepository {

    private final JPAApi jpaApi;
    private final PostExecutionContext ec;
    private final CircuitBreaker circuitBreaker = new CircuitBreaker().withFailureThreshold(1).withSuccessThreshold(3);

    @Inject
    public JPAPostRepository(JPAApi api, PostExecutionContext ec) {
        this.jpaApi = api;
        this.ec = ec;
    }

    @Override
    public CompletionStage<Stream<PostData>> list() {
        return supplyAsync(() -> wrap(em -> select(em)), ec);
    }

    @Override
    public CompletionStage<PostData> create(PostData postData) {
        return supplyAsync(() -> wrap(em -> insert(em, postData)), ec);
    }

    @Override
    public CompletionStage<Optional<PostData>> get(Long id) {
        return supplyAsync(() -> wrap(em -> Failsafe.with(circuitBreaker).get(() -> lookup(em, id))), ec);
    }

    @Override
    public CompletionStage<Optional<String>> update(Long id, PostData postData) {
        System.out.println("Repository update id=" + id);
        return supplyAsync(() -> wrap(em -> Failsafe.with(circuitBreaker).get(() -> modify(em, id, postData))), ec);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Optional<PostData> lookup(EntityManager em, Long id) throws SQLException {
        return Optional.ofNullable(em.find(PostData.class, id));
    }

    private Stream<PostData> select(EntityManager em) {
        TypedQuery<PostData> query = em.createQuery("SELECT p FROM PostData p", PostData.class);
        return query.getResultList().stream();
    }

    private Optional<String> modify(EntityManager em, Long id, PostData postData) throws InterruptedException {
        System.out.println("Repository update id=" + id);
        final PostData data = em.find(PostData.class, id);
        if (data != null) {
            data.title = postData.title;
            data.body = postData.body;
        }
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<PostData> update = cb.createCriteriaUpdate(PostData.class);
        Root e = update.from(PostData.class);
        update.set("id", id);
        update.where(cb.equal(e.get("id"), id));
        int i = em.createQuery(update).executeUpdate();
        String status = "Fail";
        if (i > 0) {
            status = "Success";
        }
        return Optional.of(status);
    }

    private PostData insert(EntityManager em, PostData postData) {
        return em.merge(postData);
    }
}
