package v1.car;

import org.apache.ibatis.session.SqlSession;
import play.db.jpa.JPAApi;
import v1.MyBatisUtil;
import v1.car.entity.Car;
import v1.car.entity.CarDataMapper;
import v1.car.entity.CarExt;
import v1.post.PostExecutionContext;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
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

    public CompletionStage<Stream<CarExt>> list() {
        System.out.println("CarRepositoryImpl.list");
        SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession(true);
        CarDataMapper mapper = s.getMapper(CarDataMapper.class);
        List<CarExt> listCar = mapper.list();
        return supplyAsync(listCar::stream);
    }

    public CompletionStage<Car> create(Car car) {
        System.out.println("CarRepositoryImpl.create " + car);
        SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession(true);
        CarDataMapper mapper = s.getMapper(CarDataMapper.class);
        mapper.create(car);
        return supplyAsync(() -> car);
    }

    public CompletionStage<Optional<Car>> get(Long id) {
        return null;
    }

    public CompletionStage<Optional<Integer>> update(Car car) {
        System.out.println("CarRepositoryImpl.update " + car);
        SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession(true);
        CarDataMapper mapper = s.getMapper(CarDataMapper.class);
        int rowNumber = mapper.update(car);
        return supplyAsync(() -> Optional.of(rowNumber));
    }
}
