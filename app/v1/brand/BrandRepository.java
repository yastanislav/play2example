package v1.brand;

import v1.brand.entity.Brand;

import java.util.concurrent.CompletionStage;

public interface BrandRepository {

    CompletionStage<Brand> create(Brand model);

//    CompletionStage<Optional<Brand>> get(Long id);
}
