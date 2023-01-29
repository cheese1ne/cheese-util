package com.cheese.util.codenav.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


/**
 * 核心数据库配置信息
 * @author sobann
 */

@Data
@Component
@PropertySource(value = {"classpath:datasource-codenav.properties"})
@ConfigurationProperties(prefix = "spring.datasource.code-nav")
public class DataSourceCodeNavProperty {
    private String driverClassName;
    private String jdbcUrl;
    private String username;
    private String password;
    private Class<DataSource> type;
}
