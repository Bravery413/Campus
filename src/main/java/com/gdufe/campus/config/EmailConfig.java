package com.gdufe.campus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author bravery
 * @date 2019/8/12 8:15
 */
@Configuration
@ConfigurationProperties(prefix="email")
@Data
public class EmailConfig {
    private String hostName;

    private String sendFromEmailAddress;

    private String sendFromName;

    private String emailPassword;

    private String emailTitle;
}
