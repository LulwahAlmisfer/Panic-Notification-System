package com.panic.panicnotificationservice.security;

import com.panic.panicnotificationservice.services.ClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final ClientService clientService;

    @Value("${admin-key}")
    String adminToken;

    public SecurityConfiguration(ClientService clientService) {
        this.clientService = clientService;
    }
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new ApiKeyFilter(clientService, adminToken), AnonymousAuthenticationFilter.class)
                .authorizeHttpRequests(requests ->
                requests
                        .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/v1/client/create")).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/v1/client/create");
    }

}
