package v1.car.entity;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandDataMapper {

    @Select("select id, name, country from model where title='#{name}'")
    List<Car> findByName(String name);
}
