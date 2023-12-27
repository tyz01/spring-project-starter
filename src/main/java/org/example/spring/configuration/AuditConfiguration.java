package org.example.spring.configuration;

import org.example.Main;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
@EnableEnversRepositories(basePackageClasses = Main.class)
public class AuditConfiguration {
    @Bean
    public AuditorAware<String> auditorAware() {
        // SecurityContext.getCurrentUser.getId() || getUsername()
        return () -> Optional.of("tyz");
    }
}
