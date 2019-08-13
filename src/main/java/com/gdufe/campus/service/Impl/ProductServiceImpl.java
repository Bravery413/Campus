package com.gdufe.campus.service.Impl;

import com.gdufe.campus.mapper.ProductMapper;
import com.gdufe.campus.pojo.DTO.ProductDTO;
import com.gdufe.campus.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bravery
 * @date 2019/8/13 10:58
 */

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    //TODO 写到这里

    @Override
    public List<ProductDTO> listAll() {
        return null;
    }

    @Override
    public List<ProductDTO> findByUser(Long uid) {
        return null;
    }

    @Override
    public ProductDTO findById(Long id) {
        return null;
    }

    @Override
    public Integer add(ProductDTO productDTO) {
        return null;
    }

    @Override
    public Integer update(ProductDTO productDTO) {
        return null;
    }

    @Override
    public Integer delete(Long id) {
        return null;
    }
}
