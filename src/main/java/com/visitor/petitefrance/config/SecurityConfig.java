package com.visitor.petitefrance.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Define an in-memory user store for authentication
    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder users = User.builder();
        return new InMemoryUserDetailsManager(
                users.username("user")
                      .password(passwordEncoder().encode("password"))  // Use the encoder for the password
                      .roles("USER")
                      .build()
        );
    }

    // Define a Password Encoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Define security settings without extending WebSecurityConfigurerAdapter
    @SuppressWarnings("deprecation")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Disable CSRF protection (for testing only)
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/**").permitAll()  // Allow all pages without authentication
                    .anyRequest().authenticated()  // Other pages require authentication
            );
        return http.build();
    }
}