package org.example.spring.configuration;

import org.example.spring.database.pool.ConnectionPool;
import org.example.web.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;

@Import(WebConfiguration.class)
@Configuration(proxyBeanMethods = true)
public class ApplicationConfiguration {

    @Bean("pool2")
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ConnectionPool pool2(@Value("${db.username}") String username, @Value("${db.pool.size}") Integer poolSize) {
        return new ConnectionPool(username, poolSize);
    }
    @Bean
    public ConnectionPool pool1() {
        return new ConnectionPool("username", 0);
    }

//    @Bean
//    @Profile("!prod|web") // ! & |
//    public UserRepository userRepository2(@Qualifier("pool2") ConnectionPool pool2) {
//        return new UserRepository(pool2);
//    }

//    @Bean
//    public UserRepository userRepository3() {
//        return new UserRepository(pool3());
//    }

}
