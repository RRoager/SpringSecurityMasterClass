package com.roager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                .requestMatchers("/myAccount","/myBalance","/myLoans","/myCards").authenticated()
                .requestMatchers("/notices","/contact").permitAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        /*
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123456")
                .authorities("admin")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("123456")
                .authorities("read")
                .build();

         */

        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);

        UserDetails admin = User.withUsername("admin")
                .passwordEncoder(passwordEncoder)
                .password("123456")
                .authorities("admin")
                .build();

        UserDetails user = User.withUsername("user")
                .passwordEncoder(passwordEncoder)
                .password("123456")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
