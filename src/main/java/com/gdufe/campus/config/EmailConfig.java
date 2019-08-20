package com.gdufe.campus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author bravery
 * @date 2019/8/12 8:15
 */
@Configuration
@Data
public class EmailConfig {
    private String hostName="smtp.126.com";

    private String sendFromEmailAddress="gdufee@126.com";

    private String sendFromName="校园周边App";

    private String emailPassword="py123456";

    private String emailTitle="账号注册激活";

    private String emailHost="http://192.168.10.83";
}
