package v1.car.entity;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import v1.model.entity.Model;

import java.util.List;

public interface CarDataMapper {

    @Select({"select a.id, a.year_prod, a.cost, b.title, b.year_prod_start, b.year_prod_end from car a left join model b on a.model_id=b.id"})
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "yearProd", column = "year_prod"),
            @Result(property = "model", column = "model_id", javaType = Model.class,
                    one = @One(select = "v1.model.entity.ModelDataMapper.get")),
//            @Result(property = "model.title", column = "title"),
//            @Result(property = "model.yearProdStart", column = "year_prod_s tart"),
//            @Result(property = "model.yearProdEnd", column = "year_prod_end"),
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
