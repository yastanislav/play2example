package v1.car;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import v1.car.entity.CarExt;
import v1.car.model.CreateCarRequest;
import v1.car.model.ListCarResponse;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

public class CarController extends Controller {

    private HttpExecutionContext ec;
    private CarHandler handler;

    @Inject
    public CarController(HttpExecutionContext ec, CarHandler handler) {
        this.ec = ec;
        this.handler = handler;
    }

    public CompletionStage<Result> create(Http.Request request) {
        System.out.println("*** CarController.create");
        JsonNode json = request.body().asJson();
        CreateCarRequest createCarRequest = Json.fromJson(json, CreateCarRequest.class);
        return handler.create(createCarRequest).thenApplyAsync(
                createCarResponse -> created(Json.toJson(createCarResponse)), ec.current());
    }

    public CompletionStage<Result> list(Http.Request request) {
        System.out.println("CarController.list");
        return handler.list().thenApplyAsync(
                streamCarList -> {
                    final List<CarExt> carList = streamCarList.collect(Collectors.toList());
                    ListCarResponse lcr = new ListCarResponse(carList);
                    return ok(Json.toJson(lcr));
                }, ec.current());
    }
}
