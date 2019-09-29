package v1.car.entity;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;
import v1.brand.entity.Brand;
import v1.model.entity.Model;

import java.util.List;

public interface CarDataMapper {

    @Select({"select id, year_prod, cost, model_id from car"})
    @Results(value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "yearProd", column = "year_prod"),
            @Result(property = "brand", column = "model_id", javaType = Brand.class,
                    one = @One(select = "v1.car.entity.BrandDataMapper.getByModelId", fetchType = FetchType.EAGER)),
            @Result(property = "model", column = "model_id", javaType = Model.class,
                    one = @One(select = "v1.model.entity.ModelDataMapper.get", fetchType = FetchType.EAGER)),
            @Result(property = "cost", column = "cost")
    })
    List<CarExt> list();

    @Insert("insert into car(model_id, brand_id, year_prod, cost) values (#{modelId},#{brandId},#{yearProd},#{cost})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(Car car);

    @Delete("delete from car where id=#{id}")
    void delete(Long id);

    @Update("update car set model_id=#{modelId}, brand_id=#{brandId}, year_prod = #{yearProd}, cost=#{cost} where id=#{id}")
    void update(Long id, Long modelId, Long brandId, String yearProd, String cost);
}
