package v1.car.entity;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandDataMapper {

    @Select("select id, name, country from model where title='#{name}'")
    List<Car> findByName(String name);

    @Select({"select b.id, b.name, b.country from brand b join model m on b.id=m.brand_id where m.id=#{id}"})
    @Results(value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "country", column = "country")
    })
    Brand getByModelId(Long id);
}
