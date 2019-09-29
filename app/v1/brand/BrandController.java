package v1.brand;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import v1.brand.model.CreateBrandRequest;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class BrandController extends Controller {

    private HttpExecutionContext ec;
    private BrandHandler handler;

    @Inject
    public BrandController(HttpExecutionContext ec, BrandHandler handler) {
        this.ec = ec;
        this.handler = handler;
    }

    public CompletionStage<Result> create(Http.Request request) {
        System.out.println("*** BrandController.create");
        JsonNode json = request.body().asJson();
        CreateBrandRequest createBrandRequest = Json.fromJson(json, CreateBrandRequest.class);
        return handler.create(createBrandRequest).thenApplyAsync(
                response -> created(Json.toJson(response)), ec.current());
    }

}
