package v1.brand.entity;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface BrandDataMapper {

    @Select({"select b.id, b.name, b.country from brand b join model m on b.id=m.brand_id where m.id=#{id}"})
    @Results(value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "country", column = "country")
    })
    Brand getByModelId(Long id);

    @Insert("insert into brand(name, country) values (#{name},#{country})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(Brand brand);
}
