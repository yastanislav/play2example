package v1.car;

import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Http;
import v1.car.model.CreateCarRequest;
import v1.post.entity.PostData;
import v1.post.model.PostResource;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class CarHandler {

    private final CarRepository repository;
    private final HttpExecutionContext ec;

    @Inject
    public CarHandler(CarRepository repository, HttpExecutionContext ec) {
        this.repository = repository;
        this.ec = ec;
    }

    public CompletionStage<PostResource> create(Http.Request request, CreateCarRequest resource) {
        System.out.println("CarHandler.create" + request.toString());
        final PostData data = new PostData(resource.getTitle(), resource.getBody());
        return repository.create(data).thenApplyAsync(
                savedData -> new PostResource(savedData, link(request, savedData)), ec.current());
    }

//    public CompletionStage<List<PostData>> findByTitle(Http.Request request, String title) {
//        SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession(true);
//        System.out.println("session =" + s);
//        CarDataMapper mapper = s.getMapper(CarDataMapper.class);
//        return supplyAsync(() -> (mapper.findByTitle(title)));
//    }

}
