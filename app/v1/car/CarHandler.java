package v1.car;

import play.libs.concurrent.HttpExecutionContext;
import v1.car.entity.Car;
import v1.car.model.CreateCarRequest;
import v1.car.model.CreateCarResponse;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

import static v1.car.model.CarResponse.FAIL;
import static v1.car.model.CarResponse.SUCCESS;

public class CarHandler {

    private final CarRepository repository;
    private final HttpExecutionContext ec;

    @Inject
    public CarHandler(CarRepository repository, HttpExecutionContext ec) {
        this.repository = repository;
        this.ec = ec;
    }

//public CompletionStage<List<PostData>> findByTitle(Http.Request request, String title) {
//        SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession(true);
//        System.out.println("session =" + s);
//        CarDataMapper mapper = s.getMapper(CarDataMapper.class);
//        return supplyAsync(() -> (mapper.findByTitle(title)));
//    }

    public CompletionStage<CreateCarResponse> create(CreateCarRequest createCarRequest) {
        System.out.println("CarHandler.create");
        Car inputCar = new Car(createCarRequest);
        return repository.create(inputCar).thenApplyAsync(
                outputCar -> {
                    System.out.println("outputCar="+outputCar);
                    String status = (outputCar.getId() != null) ? SUCCESS : FAIL;
                    return new CreateCarResponse(outputCar.getId(), status);
                }, ec.current());
    }

}
