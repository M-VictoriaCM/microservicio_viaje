package com.microservicio_viaje.microservicio_viaje.Config;

import com.microservicio_viaje.microservicio_viaje.JWT.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())  //deshabilita la protección CSRF (Cross-Site Request Forgery)
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/service_viaje/**").permitAll()
                                .requestMatchers("/monopatin/**").permitAll()
                                .anyRequest().authenticated() //requiere autenticación para cualquier otra solicitud
                )
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //configura la política de creación de sesión como sin estado (stateless)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) //agrega el filtro JWT antes del filtro de nombre de usuario y contraseña
                .build(); //construye y devuelve la cadena de filtros de seguridad
    }
}
