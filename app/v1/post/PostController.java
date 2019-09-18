package v1.post;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import v1.post.model.PostResource;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@With(PostAction.class)
public class PostController extends Controller {


    private HttpExecutionContext ec;
    private PostResourceHandler handler;

    @Inject
    public PostController(HttpExecutionContext ec, PostResourceHandler handler) {
        this.ec = ec;
        this.handler = handler;
    }

    public CompletionStage<Result> findByTitle(Http.Request request) {
        String title = request.getQueryString("title");
        System.out.println("findByTitle title = "+title);
        return handler.findByTitle(request, title).thenApplyAsync(posts -> ok(Json.toJson(posts)), ec.current());
    }

    public CompletionStage<Result> list(Http.Request request) {
        System.out.println("request.body().asText() = "+request.body().asText());
        return handler.find(request).thenApplyAsync(posts -> {
            final List<PostResource> postList = posts.collect(Collectors.toList());
            return ok(Json.toJson(postList));
        }, ec.current());
    }

    public CompletionStage<Result> show(Http.Request request, String id) {
        System.out.println("show id = "+id);
        return handler.lookup(request, id).thenApplyAsync(
                optionalResource -> optionalResource.map(resource -> ok(Json.toJson(resource))
        ).orElseGet(Results::notFound), ec.current());
    }

    public CompletionStage<Result> update(Http.Request request, String id) {
        JsonNode json = request.body().asJson();
        System.out.println("Update BODY as JSON =" + request.body().asJson());
        PostResource resource = Json.fromJson(json, PostResource.class); // маппинг входящего json на java-класс PostResources
        return handler.update(id, resource).thenApplyAsync(optionalResource -> {
            return optionalResource.map(r ->
                    ok(Json.toJson(r))
            ).orElseGet(Results::notFound
            );
        }, ec.current());
    }

    public CompletionStage<Result> create(Http.Request request) {
        JsonNode json = request.body().asJson();
        System.out.println("request.body().asText() = "+request.body());
        final PostResource resource = Json.fromJson(json, PostResource.class);
        return handler.create(request, resource).thenApplyAsync(savedResource -> {
            return created(Json.toJson(savedResource));
        }, ec.current());
    }
}
