package com.cheese.util.codenav.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.cheese.util.codenav.property.DruidConfigProperty;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * druid数据源配置
 * @author choot
 * @date 2019/9/3 17:41
 * @description
 */
@Configuration
public class DruidConfiguration {

//    @Bean
    public DruidDataSource druidDataSource(@Qualifier("druidConfigProperty") DruidConfigProperty druidConfigProperty){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setInitialSize(druidConfigProperty.getInitialSize());
        druidDataSource.setMinIdle(druidConfigProperty.getMinIdle());
        druidDataSource.setMaxActive(druidConfigProperty.getMaxActive());
        druidDataSource.setMaxWait(druidConfigProperty.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(druidConfigProperty.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(druidConfigProperty.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(druidConfigProperty.getValidationQuery());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(druidConfigProperty.getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setConnectProperties(druidConfigProperty.getConnectionProperties());
        return druidDataSource;
    }
    /**
     * 配置监控服务器
     * 访问http://localhost:9966/druid/login.html
     * 用户名：druid
     * 密码：123456
     * 用于监控慢sql引发的接口返回超时的情况
     * @return 返回监控注册的servlet对象
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet(){
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //添加初始化参数：initParams
        //白名单：允许外部ip访问
        servletRegistrationBean.addInitParameter("allow","");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny","");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
