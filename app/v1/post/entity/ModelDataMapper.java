package v1.post.entity;

import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ModelDataMapper {

    @Select("select id, title, year_prod_start, year_prod_end from model where title='#{title}'")
    List<Car> findByTitle(String title);

}
