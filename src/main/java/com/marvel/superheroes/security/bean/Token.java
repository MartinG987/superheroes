package com.marvel.superheroes.security.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Bean que representa el token de seguridad
 * @author Tincho
 *
 */
@Data
@AllArgsConstructor
public class Token {

	private String token;
}
