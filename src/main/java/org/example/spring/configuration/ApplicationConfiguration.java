package org.example.spring.configuration;

import org.example.spring.database.pool.ConnectionPool;
import org.example.spring.database.repository.CrudRepository;
import org.example.spring.database.repository.UserRepository;
import org.example.web.WebConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;

@Import(WebConfiguration.class)
@Configuration(proxyBeanMethods = false)
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "org.example",
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION,
                        value = ComponentScan.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                        value = CrudRepository.class),
                @ComponentScan.Filter(type = FilterType.REGEX,
                        pattern = "com\\..+Repository"),
        })
public class ApplicationConfiguration {

    @Bean("pool2")
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ConnectionPool pool2(@Value("${db.username}") String username, @Value("${db.poolsize}") Integer poolsize) {
        return new ConnectionPool(username, poolsize);
    }
    @Bean
    public ConnectionPool pool3() {
        return new ConnectionPool("username", 0);
    }

    @Bean
    @Profile("!prod|web") // ! & |
    public UserRepository userRepository2(@Qualifier("pool2") ConnectionPool pool2) {
        return new UserRepository(pool2);
    }

    @Bean
    public UserRepository userRepository3() {
        return new UserRepository(pool3());
    }
}
