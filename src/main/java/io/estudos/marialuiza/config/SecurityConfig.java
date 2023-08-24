package io.estudos.marialuiza.config;

import io.estudos.marialuiza.security.jwt.JwtAuthFilter;
import io.estudos.marialuiza.security.jwt.JwtService;
import io.estudos.marialuiza.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    @Lazy
    private UsuarioServiceImpl usuarioService;
    @Autowired
    private JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                        .antMatchers("/api/clientes/**").hasAnyRole("USER","ADMIN" )
                        .antMatchers("/api/produtos/**").hasAnyRole("ADMIN")
                        .antMatchers("/api/pedidos/**").hasAnyRole("USER", "ADMIN")
                        .antMatchers(HttpMethod.POST, "/api/usuarios/**").permitAll()
                        .anyRequest().authenticated()
                )
                .userDetailsService(usuarioService)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore( jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
