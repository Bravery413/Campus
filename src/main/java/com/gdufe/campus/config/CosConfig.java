package com.gdufe.campus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author bravery
 * @date 2019/8/12 8:15
 */
@Configuration
@ConfigurationProperties(prefix="cos")
@Data
public class CosConfig {
    private String accessKey;

    private String secretKey;

    private String bucketName;

    private String appId;

    private String regionId;
}
