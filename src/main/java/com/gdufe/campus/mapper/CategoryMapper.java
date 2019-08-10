package com.gdufe.campus.mapper;

import com.gdufe.campus.pojo.DO.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author bravery
 * @date 2019/8/9 10:19
 */

public interface CategoryMapper {

    @Select("select * from category_ ")
    List<Category> findAll();

}
