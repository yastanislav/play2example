package v1.model;

import org.apache.ibatis.session.SqlSession;
import v1.MyBatisUtil;
import v1.model.entity.Model;
import v1.model.entity.ModelDataMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Singleton
public class ModelRepositoryImpl implements ModelRepository {

    @Inject
    public ModelRepositoryImpl() {
    }

    public CompletionStage<Model> create(Model model) {
        System.out.println("ModelRepositoryImpl.create " + model);
        SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession(true);
        ModelDataMapper mapper = s.getMapper(ModelDataMapper.class);
        mapper.create(model);
        return supplyAsync(() -> model);
    }

    public CompletionStage<Optional<Model>> get(Long id) {
        System.out.println("ModelRepositoryImpl.get id=" + id);
        SqlSession s = MyBatisUtil.getSqlSessionFactory().openSession(true);
        ModelDataMapper mapper = s.getMapper(ModelDataMapper.class);
        return supplyAsync(() -> Optional.ofNullable(mapper.get(id)));
    }
}
