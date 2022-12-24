package com.marvel.superheroes;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Configuracion de Swagger
 * @author Tincho
 *
 */
@Configuration
@io.swagger.v3.oas.annotations.security.SecurityScheme(
  name = "Bearer Authentication",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  scheme = "bearer"
)
@OpenAPIDefinition(info = @Info(title = "API SuperHeroes", version = "1.0", description = ""))
public class OpenApiConfig {

}