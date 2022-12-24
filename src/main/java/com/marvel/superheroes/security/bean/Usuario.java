package com.marvel.superheroes.security.bean;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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

	@NotEmpty()
	@Size(min=3,max=50)
	private String username;
	@NotEmpty()
	@Size(min=3,max=50)
	private String password;
	
}
