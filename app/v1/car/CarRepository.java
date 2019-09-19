package v1.car;

import v1.car.entity.Car;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public interface CarRepository {

    CompletionStage<Stream<Car>> list();

    CompletionStage<Car> create(Car car);

    CompletionStage<Optional<Car>> get(Long id);

    CompletionStage<Optional<String>> update(Long id, Car car);
}
