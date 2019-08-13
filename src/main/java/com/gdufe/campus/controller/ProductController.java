package com.gdufe.campus.controller;

import com.gdufe.campus.enums.ResultEnum;
import com.gdufe.campus.pojo.DTO.ProductDTO;
import com.gdufe.campus.pojo.VO.ProductVO;
import com.gdufe.campus.pojo.VO.ResultVO;
import com.gdufe.campus.service.Impl.ProductServiceImpl;
import com.gdufe.campus.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author bravery
 * @date 2019/8/13 15:22
 */
@Controller
@RequestMapping("/product")
@Slf4j
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/listPage")
    public String listPage() {
        return "/product/product_list";
    }

    @GetMapping("/addPage")
    public String addPage() {
        return "/product/product_add";
    }

    @GetMapping("/detailPage")
    public String detailPage() {
        return "/product/product_detail";
    }

    @ResponseBody
    @PostMapping("/list")
    public ResultVO listAll() {
        List<ProductDTO> productDTOS = productService.listAll();
        return ResultVOUtil.success(productDTOS);
    }

    @ResponseBody
    @PostMapping("/detail")
    public ResultVO detail (Long id) {
        ProductDTO productDTO = productService.findById(id);
        return ResultVOUtil.success(productDTO);
    }

    @ResponseBody
    @PostMapping("/add")
    public ResultVO add(ProductVO productVO) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(productVO,productDTO);
        Integer add = productService.add(productDTO);
        if (add==1){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.ADD_FAILED.getCode(),
                ResultEnum.ADD_FAILED.getMessage());
    }
    @ResponseBody
    @PostMapping("/update")
    public ResultVO update(ProductVO productVO) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(productVO,productDTO);
        Integer update = productService.update(productDTO);
        if (update==1){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.UPDATE_FAILED.getCode(),
                ResultEnum.UPDATE_FAILED.getMessage());
    }
    @ResponseBody
    @GetMapping("/delete")
    public ResultVO update(Long id) {
        Integer delete = productService.delete(id);
        if (delete==1){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.UPDATE_FAILED.getCode(),
                ResultEnum.UPDATE_FAILED.getMessage());
    }

    //TODO COS储存桶

}
