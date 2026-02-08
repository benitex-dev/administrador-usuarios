package com.plataforma_educativa.admnistrador_usuarios.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    public SecurityFilterChain filterChain(HttpSecurity httpSec) throws Exception{
      return   httpSec
                .csrf(csrf->csrf.disable())
                .httpBasic(Customizer.withDefaults())
                 .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(http->
                        //endpoints publicos
                      { http.requestMatchers(HttpMethod.GET,"/homenoseg").permitAll();
                        http.requestMatchers(HttpMethod.GET,"/homeseg").hasAuthority("READ");
                        http.anyRequest().denyAll();
                      }).build();


    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService () {
        List userDetailsList = new ArrayList<>();

        userDetailsList.add(User.withUsername("admin")
                .password("1234") // esto si no está codificado, sino, tiene que seguir el algoritmo de codificación
                .roles("ADMINISTRADOR")
                .authorities("CREATE", "READ", "UPDATE", "DELETE")
                .build());

        userDetailsList.add(User.withUsername("alumno")
                .password("1234") // esto si no está codificado, sino, tiene que seguir el algoritmo de codificación
                .roles("ESTUDIANTE")
                .authorities("READ","UPDATE")
                .build());

        userDetailsList.add(User.withUsername("profe")
                .password("1234") // esto si no está codificado, sino, tiene que seguir el algoritmo de codificación
                .roles("PROFESOR")
                .authorities("UPDATE","READ","CREATE")
                .build());

        userDetailsList.add(User.withUsername("prueba")
                .password("1234") // esto si no está codificado, sino, tiene que seguir el algoritmo de codificación
                .roles("ESTUDIANTE")
                .authorities("UPDATE")
                .build());

        return new InMemoryUserDetailsManager(userDetailsList);
    }

}
