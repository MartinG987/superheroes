package com.marvel.superheroes.exception;

/**
 * Custom Exception
 * @author Tincho
 *
 */
public class UserValidation extends Exception {

	private static final long serialVersionUID = 1L;

	public UserValidation(String msj) {
		super(msj);
	}
	
}
