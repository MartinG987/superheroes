package com.marvel.superheroes.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.marvel.superheroes.exception.UserValidation;
import com.marvel.superheroes.security.bean.Token;
import com.marvel.superheroes.security.bean.Usuario;
import com.marvel.superheroes.utils.JWTUtils;

/**
 * Implementacion del servicio de usuario
 * @author Tincho
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Value("${user_api}")
	private String user;

	@Value("${password_api}")
	private String password;
	
	@Override
	public Token validate(Usuario user) throws UserValidation {
		if (validateUserAndPassword(user)) 
			return generateToken(user);
		throw new UserValidation("Error de Validacion");
	}

	//TODO: Pasar la logica de usuario a base u otro lado segun corresponda
	private boolean validateUserAndPassword(Usuario user) {
		return user.getUsername().equals(this.user) && user.getPassword().equals(password);
	}


	private Token generateToken(Usuario user) {
		return new Token(JWTUtils.getJWTToken(user.getUsername()));
	}

}
