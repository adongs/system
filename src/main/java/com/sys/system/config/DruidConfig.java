package com.sys.system.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 阿里 druid配置
 *
 * @Author yudong
 * @Date 2018年05月02日 上午11:47
 */
@Configuration
public class DruidConfig {

    @Autowired
    private DruidParameterConfig druidParameter;

    /**
     * 注册一个StatViewServlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean DruidStatViewServle2() {
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid2/*");
        //添加初始化参数：initParams
        //白名单：
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "admin2");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter2() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
        return filterRegistrationBean;
    }

    /**
     * 配置 DataSource
     *
     * @return
     */
    @Bean
    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(druidParameter.getUrl());
        datasource.setUsername(druidParameter.getUserName());
        datasource.setPassword(druidParameter.getPassword());
        datasource.setDriverClassName(druidParameter.getDriveClassName());
        datasource.setInitialSize(druidParameter.getInitialSize());
        datasource.setMinIdle(druidParameter.getMinIdle());
        datasource.setMaxActive(druidParameter.getMaxActive());
        datasource.setMaxWait(druidParameter.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(druidParameter.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(druidParameter.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(druidParameter.getValidationQuery());
        datasource.setTestWhileIdle(druidParameter.getTestWhileIdle());
        datasource.setTestOnBorrow(druidParameter.getTestOnBorrow());
        datasource.setTestOnReturn(druidParameter.getTestOnReturn());
        try {
            datasource.setFilters(druidParameter.getFilters());
        }catch (SQLException e){
            e.printStackTrace();
        }
        return datasource;
    }

}
