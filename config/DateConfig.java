package com.example.jdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @ProjectName: jdbc
 * @Package: com.example.jdbc.config
 * @ClassName: DateConfig
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/10/17 15:58
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/10/17 15:58
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DateConfig {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Bean(name="dataSource")
    public DataSource dataSource() throws PropertyVetoException {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public JdbcTemplate  jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
