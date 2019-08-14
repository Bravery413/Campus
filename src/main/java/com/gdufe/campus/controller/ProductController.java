package com.gdufe.campus.controller;

import com.gdufe.campus.config.CosConfig;
import com.gdufe.campus.enums.ResultEnum;
import com.gdufe.campus.pojo.DTO.ProductDTO;
import com.gdufe.campus.pojo.VO.ProductVO;
import com.gdufe.campus.pojo.VO.ResultVO;
import com.gdufe.campus.service.Impl.ProductServiceImpl;
import com.gdufe.campus.utils.ResultVOUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
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

    @Autowired
    CosConfig cosConfig;

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
    public ResultVO detail(Long id) {
        ProductDTO productDTO = productService.findById(id);
        return ResultVOUtil.success(productDTO);
    }

    @ResponseBody
    @PostMapping("/add")
    public ResultVO add(ProductVO productVO) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(productVO, productDTO);
        Integer add = productService.add(productDTO);
        if (add == 1) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.ADD_FAILED.getCode(),
                ResultEnum.ADD_FAILED.getMessage());
    }

    @ResponseBody
    @PostMapping("/update")
    public ResultVO update(ProductVO productVO) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(productVO, productDTO);
        Integer update = productService.update(productDTO);
        if (update == 1) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.UPDATE_FAILED.getCode(),
                ResultEnum.UPDATE_FAILED.getMessage());
    }

    @ResponseBody
    @GetMapping("/delete")
    public ResultVO update(Long id) {
        Integer delete = productService.delete(id);
        if (delete == 1) {
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.UPDATE_FAILED.getCode(),
                ResultEnum.UPDATE_FAILED.getMessage());
    }

    //TODO COS储存桶

    /**
     * cos储存桶创建连接
     *
     * @return
     */
    public  COSClient getCosClient() {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(cosConfig.getAccessKey(), cosConfig.getSecretKey());
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region(cosConfig.getRegionId()));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        //String bucketName = BUCKETNAME;
        return cosClient;
    }
    /**
     * 上传单张图片
     *
     * @param localFile
     * @return
     */
    public String uploadFile(File localFile) {
//        File localFile = new File("d:\\bg.jpg");
        String fileName = localFile.getName();
        int suffix = fileName.lastIndexOf(".");
        String last_str = fileName.substring(suffix + 1);
        String time_str = String.valueOf(System.currentTimeMillis());
        String imgUrl = time_str + "." + last_str;

        PutObjectRequest putObjectRequest = new PutObjectRequest(cosConfig.getBucketName(), imgUrl, localFile);
        // 设置存储类型, 默认是标准(Standard), 低频(standard_ia),一般为标准的
        putObjectRequest.setStorageClass(StorageClass.Standard);

        COSClient cc = getCosClient();
        try {
            PutObjectResult putObjectResult = cc.putObject(putObjectRequest);
            // putobjectResult会返回文件的etag
//            String etag = putObjectResult.getETag();
//            System.out.println(etag);
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }
        // 关闭客户端

        cc.shutdown();
        return imgUrl;
    }

    /**
     * 上传多张图片,最多9张
     *
     * @param files
     * @return
     */
    public  String[] uploadFiles(File[] files) throws Exception {
        int i = 0;
        String[] urls = new String[9];
        for (File f : files) {
            String imgUrl = uploadFile(f);
            urls[i] = imgUrl;
            i++;
        }
        return urls;
    }


    /**
     * 删除文件
     *
     * @param bucketName
     * @param key
     */
    public  void deleteFile(String bucketName, String key) {
        COSClient cc = getCosClient();
        try {
            cc.deleteObject(bucketName, key);
        } catch (CosClientException e) {
            e.printStackTrace();
        } finally {
            cc.shutdown();
        }

    }


}
