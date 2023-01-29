package com.cheese.util.codenav.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 目标数据库配置信息
 * @author sobann
 */
@Data
@Component
@PropertySource(value = {"classpath:datasource-target.properties"})
@ConfigurationProperties(prefix = "spring.datasource.target")
public class DataSourceTargetNavProperty {
    private String driverClassName;
    private String jdbcUrl;
    private String username;
    private String password;
    private Class<DataSource> type;
}
