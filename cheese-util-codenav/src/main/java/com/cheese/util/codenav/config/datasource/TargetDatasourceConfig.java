package com.cheese.util.codenav.config.datasource;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.cheese.util.codenav.property.DataSourceTargetNavProperty;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 多数据源配置
 * 目标数据库配置
 *
 * @author sobann
 */
@Configuration
@MapperScan(basePackages = "com.cheese.util.codenav.modules.targetnav", sqlSessionTemplateRef = "targetSqlSessionTemplate")
public class TargetDatasourceConfig {

    public static final String DATASOURCE_NAME = "targetDatasource";
    public static final String FACTORY_NAME = "targetSqlSessionFactory";
    public static final String TRANSACTION_MANAGER_NAME = "targetTransactionManager";
    public static final String SESSION_TEMPLATE_NAME = "targetSqlSessionTemplate";
    public static final String XML_FILE_PATH = "classpath:mapper/targetnav/**/*Mapper.xml";

    @Bean(name = DATASOURCE_NAME)
    public DataSource targetDataSource(@Qualifier("dataSourceTargetNavProperty") DataSourceTargetNavProperty targetDataSourceProperty) throws Exception {
        return DataSourceBuilder.create()
                .type(targetDataSourceProperty.getType())
                .driverClassName(targetDataSourceProperty.getDriverClassName())
                .url(targetDataSourceProperty.getJdbcUrl())
                .username(targetDataSourceProperty.getUsername())
                .password(targetDataSourceProperty.getPassword())
                .build();
    }

    @Bean(name = TRANSACTION_MANAGER_NAME)
    public DataSourceTransactionManager transactionManager(@Qualifier(DATASOURCE_NAME) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = FACTORY_NAME)
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
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier(FACTORY_NAME) SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
