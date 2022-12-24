package com.marvel.superheroes.controller.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marvel.superheroes.anotation.Traceable;
import com.marvel.superheroes.controller.BaseController;
import com.marvel.superheroes.exception.UserValidation;
import com.marvel.superheroes.security.bean.Token;
import com.marvel.superheroes.security.bean.Usuario;
import com.marvel.superheroes.service.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller que agrupa la funcionalidad para obtencion de token
 * @author Tincho
 *
 */
@RestController
@Tag(name = "Usuarios", description = "Funcionalidad para obtener el token")
public class UsuarioController extends BaseController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@PostMapping("/getToken")
	@Traceable
	public ResponseEntity<Token> getToken(@RequestBody Usuario user) throws UserValidation {
		return new ResponseEntity<>(usuarioService.validate(user), HttpStatus.OK);
	}
	
}