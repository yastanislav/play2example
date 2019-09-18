package v1.post;

import org.apache.ibatis.annotations.Select;
import v1.post.entity.Car;

import java.util.List;

public interface CarDataMapper {

    @Select("select a.id, a.yearProd, a.cost, b.title, b.year_prod_start, b.year_prod_end from car a join model b on a.model_id=b.id where a.title like #{title}")
    List<Car> findByModelTitle(String title);
}
