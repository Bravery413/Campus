package com.gdufe.campus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdufe.campus.pojo.DO.ProductDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author bravery
 * @date 2019/8/13 9:08
 */
@Mapper
@Component
public interface ProductMapper extends BaseMapper<ProductDO> {

    @Select("select * from goods where uid=#{uid} ")
    List<ProductDO> findByUser(Long uid);

}
