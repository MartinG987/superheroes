package com.marvel.superheroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.marvel.superheroes.security.JWTAuthorizationFilter;

/**
 * Main Class & Configuracion de Seguridad
 * @author Tincho
 *
 */
@SpringBootApplication
public class SuperheroesApplication {

	private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs", 
            "/swagger-resources/**", 
            "/configuration/ui",
            "/configuration/security", 
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger-ui/**",
            "/v3/**"
    };
	
	
	public static void main(String[] args) {
		SpringApplication.run(SuperheroesApplication.class, args);
	}
	
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig {
		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/getToken").permitAll()
				.antMatchers(AUTH_WHITELIST).permitAll()
				.anyRequest().authenticated();
			 return http.build();
		}
	}

}
