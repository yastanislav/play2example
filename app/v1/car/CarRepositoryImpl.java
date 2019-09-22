package v1.car;

import v1.car.entity.Car;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Singleton
public class CarRepositoryImpl implements CarRepository {

    public CompletionStage<Stream<Car>> list() {
        return null;
    }

    public CompletionStage<Car> create(Car car) {
        return supplyAsync(() -> car);
    }

    public CompletionStage<Optional<Car>> get(Long id) {
        return null;
    }

    public CompletionStage<Optional<String>> update(Long id, Car car) {
        return null;
    }
}
