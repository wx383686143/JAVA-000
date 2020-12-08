package org.geekbang.ecommerce.config;

import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@Configuration
public class JdbcTemplateConfig {

    @Primary
    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = null;
        try {
            File file = new ClassPathResource("shardingJdbc.yml").getFile();
            DataSource dataSource = YamlShardingSphereDataSourceFactory.createDataSource(file);
            jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jdbcTemplate;
    }
}
