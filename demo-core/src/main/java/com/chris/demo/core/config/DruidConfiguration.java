//package com.chris.demo.core.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.support.http.StatViewServlet;
//import com.alibaba.druid.support.http.WebStatFilter;
//import lombok.Data;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
///**
// * Created by xiabin on 2017/6/18.
// */
//@Configuration
//public class DruidConfiguration {
//
//    private static final Logger logger = LoggerFactory.getLogger(DruidConfiguration.class);
//
//    private static final String DB_PREFIX = "spring.datasource";
//
//    @Bean
//    public ServletRegistrationBean druidServlet() {
//        logger.info("init Druid Servlet Configuration ");
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/config/*");
//        // IP白名单
//        servletRegistrationBean.addInitParameter("allow", "192.168.2.25,127.0.0.1");
//        // IP黑名单(共同存在时，deny优先于allow)
//        servletRegistrationBean.addInitParameter("deny", "192.168.1.100");
//        //控制台管理用户
//        servletRegistrationBean.addInitParameter("loginUsername", "admin");
//        servletRegistrationBean.addInitParameter("loginPassword", "9527");
//        //是否能够重置数据 禁用HTML页面上的“Reset All”功能
//        servletRegistrationBean.addInitParameter("resetEnable", "false");
//        return servletRegistrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/config/*");
//        return filterRegistrationBean;
//    }
//
//    //解决 spring.datasource.filters=stat,wall,log4j 无法正常注册进去
//    @Data
//    @ConfigurationProperties(prefix = DB_PREFIX)
//    class IDataSourceProperties {
//        private String url;
//        private String username;
//        private String password;
//        private String driverClassName;
//        private int initialSize;
//        private int minIdle;
//        private int maxActive;
//        private int maxWait;
//        private int timeBetweenEvictionRunsMillis;
//        private int minEvictableIdleTimeMillis;
//        private String validationQuery;
//        private boolean testWhileIdle;
//        private boolean testOnBorrow;
//        private boolean testOnReturn;
//        private boolean poolPreparedStatements;
//        private int maxPoolPreparedStatementPerConnectionSize;
//        private String filters;
//        private String connectionProperties;
//
//        @Bean     //声明其为Bean实例
//        @Primary  //在同样的DataSource中，首先使用被标注的DataSource
//        public DataSource dataSource() {
//            DruidDataSource datasource = new DruidDataSource();
//            datasource.setUrl(url);
//            datasource.setUsername(username);
//            datasource.setPassword(password);
//            datasource.setDriverClassName(driverClassName);
//
//            //configuration
//            datasource.setInitialSize(initialSize);
//            datasource.setMinIdle(minIdle);
//            datasource.setMaxActive(maxActive);
//            datasource.setMaxWait(maxWait);
//            datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//            datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//            datasource.setValidationQuery(validationQuery);
//            datasource.setTestWhileIdle(testWhileIdle);
//            datasource.setTestOnBorrow(testOnBorrow);
//            datasource.setTestOnReturn(testOnReturn);
//            datasource.setPoolPreparedStatements(poolPreparedStatements);
//            datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
//            try {
//                datasource.setFilters(filters);
//            } catch (SQLException e) {
//                System.err.println("config configuration initialization filter: " + e);
//            }
//            datasource.setConnectionProperties(connectionProperties);
//            return datasource;
//        }
//    }
//
//}