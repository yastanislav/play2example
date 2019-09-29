package v1.brand;

import play.libs.concurrent.HttpExecutionContext;
import v1.brand.entity.Brand;
import v1.brand.model.CreateBrandRequest;
import v1.brand.model.CreateBrandResponse;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

import static v1.CommonResponse.FAIL;
import static v1.CommonResponse.SUCCESS;

public class BrandHandler {

    private final BrandRepository repository;
    private final HttpExecutionContext ec;

    @Inject
    public BrandHandler(BrandRepository repository, HttpExecutionContext ec) {
        this.repository = repository;
        this.ec = ec;
    }

    public CompletionStage<CreateBrandResponse> create(CreateBrandRequest request) {
        System.out.println("BrandHandler.create");
        Brand input = new Brand(request);
        return repository.create(input).thenApplyAsync(
                output -> {
                    System.out.println("output = " + output);
                    String status = (output.getId() != null) ? SUCCESS : FAIL;
                    return new CreateBrandResponse(output.getId(), status);
                }, ec.current());
    }

//    public CompletionStage<Optional<Brand>> get(Http.Request request, String id) {
//        return repository.get(Long.parseLong(id)).thenApplyAsync(
//                data -> data);
//    }
}
