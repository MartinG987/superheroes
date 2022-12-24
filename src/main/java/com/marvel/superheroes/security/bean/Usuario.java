package com.marvel.superheroes.security.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Bean que representa el usuario para obtener el token
 * @author Tincho
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

	private String username;
	private String password;
	
}
