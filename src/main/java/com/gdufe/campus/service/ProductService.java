package com.gdufe.campus.service;

import com.gdufe.campus.pojo.DTO.ProductDTO;

import java.util.List;

/**
 * @author bravery
 * @date 2019/8/13 10:37
 */
public interface ProductService {

    List<ProductDTO> listAll();

    List<ProductDTO> findByUser(Long uid);

    ProductDTO findById(Long id);

    Integer add(ProductDTO productDTO);

    Integer update(ProductDTO productDTO);

    Integer delete(Long id);



}
