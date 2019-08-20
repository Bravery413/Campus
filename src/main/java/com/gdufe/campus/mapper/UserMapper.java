package com.gdufe.campus.mapper;

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
public interface UserMapper {

    @Select("select * from user ")
    List<UserDO> findAll();

    @Select("select * from user where id=#{id}")
    UserDO findById(Long id);

    @Select("select * from user where account=#{account}")
    UserDO findByAccount(String account);

    @Select("select * from user where account=#{account} and password=#{password}")
    UserDO login(UserDO user);

    @Insert("insert into user (account,password,username,email,head_img,active) " +
            "values(#{account},#{password},#{username},#{email},#{headImg},#{active})")
    int save(UserDO user);

    @Delete("delete from user where id=#{id}")
    int deleteById(int id);

    @Update("update user set account=#{account},active=#{active}," +
            "password=#{password},username=#{username},email=#{email},head_img=#{headImg} " +
            "where id=#{id}")
    int update(UserDO user);




}
