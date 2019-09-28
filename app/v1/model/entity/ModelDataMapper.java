package v1.model.entity;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface ModelDataMapper {

    @Select({"select id, title, year_prod_start, year_prod_end from model where id=#{id}"})
    @Results(value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "title", column = "title"),
            @Result(property = "yearProdStart", column = "year_prod_start"),
            @Result(property = "yearProdEnd", column = "year_prod_end"),
    })
    Model get(Long id);

    @Insert("insert into model(title, year_prod_start, year_prod_end) values (#{title},#{yearProdStart},#{yearProdEnd})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(Model model);
}
