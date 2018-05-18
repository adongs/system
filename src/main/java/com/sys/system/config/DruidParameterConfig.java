package com.sys.system.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author yudong
 * @Date 2018年05月02日 上午11:54
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidParameterConfig {
    private String driveClassName;
    private String url;
    private String userName;
    private String password;
    private String xmlLocation;
    private String typeAliasesPackage;
    private String filters;
    private int maxActive;
    private int initialSize;
    private long maxWait;
    private int minIdle;
    private long timeBetweenEvictionRunsMillis;
    private long minEvictableIdleTimeMillis;
    private String validationQuery;
    private Boolean testWhileIdle;
    private Boolean testOnBorrow;
    private Boolean testOnReturn;
    private String poolPreparedStatements;
    private String maxOpenPreparedStatements;
}
