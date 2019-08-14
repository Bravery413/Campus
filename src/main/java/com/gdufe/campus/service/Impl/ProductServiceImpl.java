package com.gdufe.campus.service.Impl;

import com.gdufe.campus.mapper.ProductMapper;
import com.gdufe.campus.pojo.DO.ProductDO;
import com.gdufe.campus.pojo.DTO.ProductDTO;
import com.gdufe.campus.service.ProductService;
import com.gdufe.campus.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<ProductDTO> listAll() {
        List<ProductDO> productDOS = productMapper.selectList(null);
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        for (ProductDO p:productDOS) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(p,productDTO);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public List<ProductDTO> findByUser(Long uid) {
        List<ProductDO> productDOS = productMapper.findByUser(uid);
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        for (ProductDO p:productDOS) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(p,productDTO);
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public ProductDTO findById(Long id) {
        ProductDO productDO = productMapper.selectById(id);
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(productDO,productDTO);
        String[] urls = productDTO.getImg().split("#");
        productDTO.setImgUrls(urls);
        return productDTO;
    }

    @Override
    public Integer add(ProductDTO productDTO) {
        ProductDO productDO = new ProductDO();
        BeanUtils.copyProperties(productDTO,productDO);
        int insert = productMapper.insert(productDO);
        return insert;
    }

    @Override
    public Integer update(ProductDTO productDTO) {
        ProductDO productDO = new ProductDO();
        BeanUtils.copyProperties(productDTO,productDO);
        int update = productMapper.updateById(productDO);
        return update;
    }

    @Override
    public Integer delete(Long id) {
        int delete = productMapper.deleteById(id);
        return delete;
    }
}
