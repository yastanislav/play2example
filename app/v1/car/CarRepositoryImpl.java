package v1.car;

import org.apache.ibatis.session.SqlSession;
import play.db.jpa.JPAApi;
import v1.car.entity.Car;
import v1.car.entity.CarDataMapper;
import v1.post.PostExecutionContext;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Singleton
public class CarRepositoryImpl implements CarRepository {

    @Inject
    public CarRepositoryImpl(JPAApi api, PostExecutionContext ec) {
//        this.jpaApi = api;
//        this.ec = ec;
    }

    public CompletionStage<Stream<Car>> list() {
        return null;
    }

    public CompletionStage<Car> create(Car car) {
        System.out.println("CarRepositoryImpl.create " + car);
        SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession(true);
        System.out.println("session =" + s);
        CarDataMapper mapper = s.getMapper(CarDataMapper.class);
        mapper.create(car);
        return supplyAsync(() -> car);
    }

    public CompletionStage<Optional<Car>> get(Long id) {
        return null;
    }

    public CompletionStage<Optional<String>> update(Long id, Car car) {
        return null;
    }
}
