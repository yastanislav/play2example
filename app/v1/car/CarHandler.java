package v1.car;

import play.libs.concurrent.HttpExecutionContext;
import v1.car.entity.Car;
import v1.car.entity.CarExt;
import v1.car.model.CreateCarRequest;
import v1.car.model.CreateCarResponse;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import static v1.CommonResponse.FAIL;
import static v1.CommonResponse.SUCCESS;

public class CarHandler {

    private final CarRepository repository;
    private final HttpExecutionContext ec;

    @Inject
    public CarHandler(CarRepository repository, HttpExecutionContext ec) {
        this.repository = repository;
        this.ec = ec;
    }

    public CompletionStage<CreateCarResponse> create(CreateCarRequest createCarRequest) {
        System.out.println("CarHandler.create");
        Car inputCar = new Car(createCarRequest);
        return repository.create(inputCar).thenApplyAsync(
                outputCar -> {
                    System.out.println("outputCar=" + outputCar);
                    String status = (outputCar.getId() != null) ? SUCCESS : FAIL;
                    return new CreateCarResponse(outputCar.getId(), status);
                }, ec.current());
    }

    public CompletionStage<Stream<CarExt>> list() {
        System.out.println("CarHandler.list");
        return repository.list().thenApplyAsync(
                output -> output.map(car -> {
                    if (car == null) System.out.println("Car equals null");
                    return car;
                }), ec.current());
    }

}
