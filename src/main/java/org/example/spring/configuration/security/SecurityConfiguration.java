package org.example.spring.configuration.security;

import org.example.spring.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        urlConfig -> urlConfig
                                .antMatchers("/login", "users/registration", "v3/api-docs/**", "swagger-ui/**").permitAll()
                                .antMatchers("/users/{\\d+}/delete").hasAuthority(Role.ADMIN.getAuthority())
                                .antMatchers("/moderator/**").hasAuthority(Role.MODERATOR.getAuthority())
                                .anyRequest().authenticated()
                )
                //  .httpBasic(Customizer.withDefaults());
                .logout(logout ->
                        logout.logoutUrl("logout")
                                .logoutSuccessUrl("/login")
                                .deleteCookies("JSESSIONID")) // default
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/users"));

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
