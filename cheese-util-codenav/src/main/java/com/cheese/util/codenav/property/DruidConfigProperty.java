package com.cheese.util.codenav.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * druid配置信息
 * @author sobann
 */
@Data
@Component
@PropertySource(value = {"classpath:druid-config.properties"})
@ConfigurationProperties(prefix = "com.alibaba.druid.pool")
public class DruidConfigProperty {
    private Integer initialSize;
    private Integer minIdle;
    private Integer maxActive;
    private Long maxWait;
    private Long timeBetweenEvictionRunsMillis;
    private Long minEvictableIdleTimeMillis;
    private String validationQuery;
    private Integer maxPoolPreparedStatementPerConnectionSize;
    private Properties connectionProperties;
}
