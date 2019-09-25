package v1.car.entity;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CarDataMapper {

    @Select({"select a.id, a.year_prod, a.cost, b.title, b.year_prod_start, b.year_prod_end from car a join model b on a.model_id=b.id where a.title like #{title}"})
    List<Car> findByModelTitle(String title);

    @Insert("insert into car(model_id, brand_id, year_prod, cost) values (#{modelId},#{brandId},#{yearProd},#{cost})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(Car car);

    @Delete("delete from car where id=#{id}")
    void delete(Long id);

    @Update("update car set model_id=#{modelId}, brand_id=#{brandId}, year_prod = #{yearProd}, cost=#{cost} where id=#{id}")
    void update(Long id, Long modelId, Long brandId, String yearProd, String cost);
}
