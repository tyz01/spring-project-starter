package org.example.spring.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "db")
public record DataBaseProperties( // instead of ConstructorBinding, Component, NoArgsConstructor, Value, Data
        String username,
        String password,
        String drier,
        String url,
        String hosts,
        PoolProperties pool,
        List<PoolProperties> polls,
        Map<String, Object> properties) {

    public static record PoolProperties(Integer size,
            Integer timeout) {
    }
}
