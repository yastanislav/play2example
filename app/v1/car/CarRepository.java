package v1.car;

import v1.car.entity.Car;
import v1.car.entity.CarExt;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public interface CarRepository {

    CompletionStage<Stream<CarExt>> list();

    CompletionStage<Car> create(Car car);

    CompletionStage<Optional<Car>> get(Long id);

    CompletionStage<Optional<Integer>> update(Car car);
}
