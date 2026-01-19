package com.learn.crm.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.builder().username("admin").password("{bcrypt}$2a$14$l6RoqQfWfbnDEI6oCd7ZMOgzPoSO.UmYW5SU/dvrhQVukpVWafAh.").build();
        return new InMemoryUserDetailsManager(user);
    }
}
