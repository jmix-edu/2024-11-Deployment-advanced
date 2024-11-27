package com.company.productmanagement2_4_0.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.vault.core.VaultTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    private final VaultTemplate vaultTemplate;

    public DatabaseConfig(VaultTemplate vaultTemplate) {
        this.vaultTemplate = vaultTemplate;
    }

    @Bean("dataSourcePropertiesDev")
    @Primary
    @Profile("default")
    @ConfigurationProperties("main.datasource")
    DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("main.datasource.hikari")
    DataSource dataSource(final DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Profile("prod")
    @Bean("dataSourcePropertiesProd")
    @Primary
    DataSourceProperties dataSourcePropertiesProd() {
        DataSourceProperties properties = new DataSourceProperties();
        DbConfig config = new ObjectMapper().convertValue(vaultTemplate.read("secret/data/application/database/credentials").getData().get("data"), DbConfig.class);

        properties.setUrl(config.getUrl());
        properties.setUsername(config.getUsername());
        properties.setPassword(config.getPassword());

        return properties;
    }


}
