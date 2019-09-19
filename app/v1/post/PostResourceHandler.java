package v1.post;

import com.palominolabs.http.url.UrlBuilder;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Http;
import v1.post.entity.PostData;
import v1.post.model.PostResource;
import v1.post.model.Response;

import javax.inject.Inject;
import java.nio.charset.CharacterCodingException;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

/**
 * Handles presentation of Post resources, which map to JSON.
 */
public class PostResourceHandler {

    private final PostRepository repository;
    private final HttpExecutionContext ec;

    @Inject
    public PostResourceHandler(PostRepository repository, HttpExecutionContext ec) {
        this.repository = repository;
        this.ec = ec;
    }

    public CompletionStage<Stream<PostResource>> find(Http.Request request) {
        return repository.list().thenApplyAsync(
                postDataStream -> postDataStream.map(data -> new PostResource(data, link(request, data))), new ForkJoinPool());
    }

    public CompletionStage<PostResource> create(Http.Request request, PostResource resource) {
        System.out.println("request.toString() = " + request.toString());
        final PostData data = new PostData(resource.getTitle(), resource.getBody());
        return repository.create(data).thenApplyAsync(
                savedData -> new PostResource(savedData, link(request, savedData)), ec.current());
    }

    public CompletionStage<Optional<PostResource>> lookup(Http.Request request, String id) {
        return repository.get(Long.parseLong(id)).thenApplyAsync(
                optionalData -> optionalData.map(data -> new PostResource(data, link(request, data))), new ForkJoinPool());
    }

    public CompletionStage<Optional<Response>> update(String id, PostResource resource) {
        final PostData data = new PostData(resource.getTitle(), resource.getBody());
        return repository.update(Long.parseLong(id), data).thenApplyAsync(
                optionalData -> optionalData.map(op -> new Response(data, op)), ec.current());
    }

    private String link(Http.Request request, PostData data) {
        final String[] hostPort = request.host().split(":");
        String host = hostPort[0];
        int port = (hostPort.length == 2) ? Integer.parseInt(hostPort[1]) : -1;
        final String scheme = request.secure() ? "https" : "http";
        try {
            return UrlBuilder.forHost(scheme, host, port)
                    .pathSegments("v1", "posts", data.id.toString())
                    .toUrlString();
        } catch (CharacterCodingException e) {
            throw new IllegalStateException(e);
        }
    }
}
