package com.gdufe.campus.mapper;

import com.gdufe.campus.pojo.DO.LessonDO;
import com.gdufe.campus.pojo.DO.UserDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author bravery
 * @date 2019/8/9 10:19
 */
@Mapper
@Component
public interface LessonMapper {

    @Select("select * from lesson ")
    List<LessonDO> findAll();

    @Select("select * from lesson where id=#{id}")
    LessonDO findById(Long id);


    @Delete("delete from lesson where id=#{id}")
    Integer deleteById(int id);

    @Update("update lesson set is_take=1 where id=#{id}")
    Integer taken(Long id);


    @Insert("insert into lesson (place,time,price,is_take,info,last_time,wechat,uid,sex,location) " +
            "values(#{place},#{time},#{price},#{isTake},#{info},#{lastTime},#{wechat},#{uid},#{sex},#{location})")
    int save(LessonDO lesson);



    @Update("update lesson set place=#{place},time=#{time}," +
            "price=#{price},is_take=#{isTake},info=#{info}," +
            "last_time=#{lastTime},wechat=#{wechat},uid=#{uid},sex=#{sex},location=#{location} " +
            "where id=#{id}")
    int update(LessonDO lesson);




}
