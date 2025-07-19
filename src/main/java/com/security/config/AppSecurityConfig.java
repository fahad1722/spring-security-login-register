package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.security.service.CustomerService;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired PasswordEncoder passwordEncoder;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(req ->
                     req.requestMatchers("/register", "/login").permitAll()
                     	.requestMatchers("/admin/**").hasRole("ADMIN")
                     	.requestMatchers("/customer/**").hasAnyRole("USER", "ADMIN") 
                        .anyRequest().authenticated()
    	)
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	authProvider.setUserDetailsService(customerService);
	authProvider.setPasswordEncoder(passwordEncoder);
	return authProvider;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	return config.getAuthenticationManager();   
    }
}
