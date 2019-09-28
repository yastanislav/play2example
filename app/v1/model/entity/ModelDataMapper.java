package v1.model.entity;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface ModelDataMapper {

    @Select("select id, title, year_prod_start, year_prod_end from model where id='#{id}'")
    Model get(Long id);

    @Insert("insert into model(title, year_prod_start, year_prod_end) values (#{title},#{yearProdStart},#{yearProdEnd})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(Model model);
}
