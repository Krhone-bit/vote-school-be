package com.team.pe.voteschool.config;

import com.team.pe.voteschool.config.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception {
        AntPathRequestMatcher h2ConsoleRequestMatcher = new AntPathRequestMatcher("/h2-console/**");
        AntPathRequestMatcher swaggerUI = new AntPathRequestMatcher("/swagger-ui/**");
        AntPathRequestMatcher docs = new AntPathRequestMatcher("/api-docs/**");
        return http
                .csrf(csrf->
                        csrf
                                .ignoringRequestMatchers(antMatcher("/h2-console/**"))
                                .disable()
                )
                .headers(AbstractHttpConfigurer::disable
                )
                .authorizeHttpRequests((authz)->
                        authz
                                .requestMatchers(antMatcher("/auth/**")).permitAll()
                                .requestMatchers(h2ConsoleRequestMatcher).permitAll()
                                .requestMatchers(swaggerUI).permitAll()
                                .requestMatchers(docs).permitAll()
                                .anyRequest().authenticated())
                .sessionManagement(sessionManager->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
