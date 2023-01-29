package com.cheese.util.codenav.config.datasource;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.cheese.util.codenav.property.DataSourceCodeNavProperty;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 多数据源配置
 * 核心数据库配置
 *
 * @author sobann
 */
@Configuration
@MapperScan(basePackages = "com.cheese.util.codenav.modules.setting", sqlSessionTemplateRef = "codeNavSqlSessionTemplate")
public class CodeNavDatasourceConfig {

    public static final String DATASOURCE_NAME = "codeNavDatasource";
    public static final String FACTORY_NAME = "codeNavSqlSessionFactory";
    public static final String TRANSACTION_MANAGER_NAME = "codeNavTransactionManager";
    public static final String SESSION_TEMPLATE_NAME = "codeNavSqlSessionTemplate";

    public static final String XML_FILE_PATH = "classpath:mapper/codenav/**/*Mapper.xml";

    @Primary
    @Bean(name = DATASOURCE_NAME)
    public DataSource codeNavDataSource(@Qualifier("dataSourceCodeNavProperty") DataSourceCodeNavProperty codeNavDataSourceProperty) {
        return DataSourceBuilder.create()
                .driverClassName(codeNavDataSourceProperty.getDriverClassName())
                .url(codeNavDataSourceProperty.getJdbcUrl())
                .username(codeNavDataSourceProperty.getUsername())
                .password(codeNavDataSourceProperty.getPassword())
                .type(codeNavDataSourceProperty.getType())
                .build();
    }

    @Bean(name = TRANSACTION_MANAGER_NAME)
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier(DATASOURCE_NAME) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = FACTORY_NAME)
    @Primary
    public SqlSessionFactory factory(@Qualifier(DATASOURCE_NAME) DataSource dataSource, PaginationInterceptor paginationInterceptor) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        bean.setConfiguration(configuration);
        bean.setPlugins(paginationInterceptor);
/*        若需要将mapper.xml文件配置到resource资源包下，使用
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(XML_FILE_PATH));*/
        return bean.getObject();
    }

    @Bean(name = SESSION_TEMPLATE_NAME)
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier(FACTORY_NAME) SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
