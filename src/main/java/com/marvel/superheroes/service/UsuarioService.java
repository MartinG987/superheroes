package com.marvel.superheroes.service;

import com.marvel.superheroes.exception.UserValidation;
import com.marvel.superheroes.security.bean.Token;
import com.marvel.superheroes.security.bean.Usuario;

/**
 * Servcio que implementa la logica para la obtencion del token de seguridad
 * @author Tincho
 *
 */
public interface UsuarioService {

	Token validate(Usuario user) throws UserValidation;

}
