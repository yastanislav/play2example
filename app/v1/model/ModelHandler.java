package v1.model;

import play.libs.concurrent.HttpExecutionContext;
import v1.model.entity.Model;
import v1.model.model.CreateModelRequest;
import v1.model.model.CreateModelResponse;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

import static v1.CommonResponse.FAIL;
import static v1.CommonResponse.SUCCESS;

public class ModelHandler {

    private final ModelRepository repository;
    private final HttpExecutionContext ec;

    @Inject
    public ModelHandler(ModelRepository repository, HttpExecutionContext ec) {
        this.repository = repository;
        this.ec = ec;
    }

    public CompletionStage<CreateModelResponse> create(CreateModelRequest createModelRequest) {
        System.out.println("CarHandler.create");
        Model inputModel = new Model(createModelRequest);
        return repository.create(inputModel).thenApplyAsync(
                outputModel -> {
                    System.out.println("outputModel = " + outputModel);
                    String status = (outputModel.getId() != null) ? SUCCESS : FAIL;
                    return new CreateModelResponse(outputModel.getId(), status);
                }, ec.current());
    }
}
