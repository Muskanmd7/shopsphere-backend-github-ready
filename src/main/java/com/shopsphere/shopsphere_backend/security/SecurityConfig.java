package com.shopsphere.shopsphere_backend.security;

import com.shopsphere.shopsphere_backend.Jwtsecurity.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(customUserDetailsService);

        provider.setPasswordEncoder(bCryptPasswordEncoder());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(cors ->
                cors.configurationSource(corsConfigurationSource)
        );

        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> auth

                // Authentication
                .requestMatchers("/register", "/login").permitAll()

                // Admin Registration
                .requestMatchers(HttpMethod.POST, "/admin/register").hasRole("ADMIN")

                // Products
                .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/products/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/products/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")

                // Categories
                .requestMatchers(HttpMethod.GET, "/categories/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/categories/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/categories/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/categories/**").hasRole("ADMIN")

                // Cart
                .requestMatchers("/cart/**").authenticated()

                // Orders
                .requestMatchers(HttpMethod.POST, "/orders/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/orders").authenticated()
                .requestMatchers(HttpMethod.GET, "/orders/all").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/orders/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/orders/**").hasRole("ADMIN")

                // Reviews
                .requestMatchers(HttpMethod.GET, "/reviews/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/reviews/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/reviews/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/reviews/**").authenticated()

                // Address
                .requestMatchers("/address/**").authenticated()

                // Dashboard
                .requestMatchers("/dashboard/**").hasRole("ADMIN")

                // Everything else
                .anyRequest().authenticated()
        );

        http.sessionManagement(session ->
                session.sessionCreationPolicy(
                        org.springframework.security.config.http.SessionCreationPolicy.STATELESS
                )
        );

        http.addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}