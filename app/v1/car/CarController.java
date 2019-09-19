package v1.car;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import v1.car.model.CreateCarRequest;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class CarController extends Controller {

    private HttpExecutionContext ec;
    private CarHandler handler;

    @Inject
    public CarController(HttpExecutionContext ec, CarHandler handler) {
        this.ec = ec;
        this.handler = handler;
    }

    public CompletionStage<Result> create(Http.Request request) {
        System.out.println("CarController.create");
        JsonNode json = request.body().asJson();
        CreateCarRequest resource = Json.fromJson(json, CreateCarRequest.class);
        return handler.create(request, resource).thenApplyAsync(savedResource -> {
            return created(Json.toJson(savedResource));
        }, ec.current());
    }
}
