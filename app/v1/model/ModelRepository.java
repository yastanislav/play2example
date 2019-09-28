package v1.model;

import v1.model.entity.Model;

import java.util.concurrent.CompletionStage;

public interface ModelRepository {

    CompletionStage<Model> create(Model model);
}
