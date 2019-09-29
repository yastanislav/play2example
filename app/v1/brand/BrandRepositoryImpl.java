package v1.brand;

import org.apache.ibatis.session.SqlSession;
import v1.MyBatisUtil;
import v1.brand.entity.Brand;
import v1.brand.entity.BrandDataMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Singleton
public class BrandRepositoryImpl implements BrandRepository {

    @Inject
    public BrandRepositoryImpl() {
    }

    public CompletionStage<Brand> create(Brand model) {
        System.out.println("BrandRepositoryImpl.create " + model);
        SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession(true);
        BrandDataMapper mapper = s.getMapper(BrandDataMapper.class);
        mapper.create(model);
        return supplyAsync(() -> model);
    }

//    public CompletionStage<Optional<Brand>> get(Long id) {
//        System.out.println("BrandRepositoryImpl.get id=" + id);
//        SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession(true);
//        BrandDataMapper mapper = s.getMapper(BrandDataMapper.class);
//        return supplyAsync(() -> Optional.ofNullable(mapper.get(id)));
//    }
}
