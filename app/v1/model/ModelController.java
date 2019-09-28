package v1.model;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import v1.model.model.CreateModelRequest;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.created;
import static play.mvc.Results.ok;

public class ModelController {

    private HttpExecutionContext ec;
    private ModelHandler handler;

    @Inject
    public ModelController(HttpExecutionContext ec, ModelHandler handler) {
        this.ec = ec;
        this.handler = handler;
    }

    public CompletionStage<Result> create(Http.Request request) {
        System.out.println("*** ModelController.create");
        JsonNode json = request.body().asJson();
        CreateModelRequest createModelRequest = Json.fromJson(json, CreateModelRequest.class);
        return handler.create(createModelRequest).thenApplyAsync(
                createModelResponse -> created(Json.toJson(createModelResponse)), ec.current());
    }

    public CompletionStage<Result> get(Http.Request request, String id) {
        System.out.println("ModelController.get id = "+id);
        return handler.get(request, id).thenApplyAsync(
                optionalResource -> optionalResource.map(resource -> ok(Json.toJson(resource))
                ).orElseGet(Results::notFound), ec.current());
    }
}
