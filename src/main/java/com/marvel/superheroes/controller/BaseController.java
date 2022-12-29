package com.marvel.superheroes.controller;

import java.security.InvalidParameterException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.marvel.superheroes.exception.DefaultExceptionAttributes;
import com.marvel.superheroes.exception.ExceptionAttributes;
import com.marvel.superheroes.exception.UserValidation;

import lombok.extern.slf4j.Slf4j;

/**
 * Manejo general de excepciones
 * @author Tincho
 *
 */
@Slf4j
public class BaseController {

	
	private ResponseEntity<?> handleBasicBusinessException(Exception exception, HttpServletRequest request,	HttpStatus httpStatus) {
		log.error("> handle" + exception.getClass().getName());
		log.error("- Exception: ", exception);
		var exceptionAttributes = new DefaultExceptionAttributes();
		var responseBody = exceptionAttributes.getExceptionAttributes(exception, request, httpStatus);
		log.error("< handle" + exception.getClass().getName());
		return new ResponseEntity<>(responseBody, httpStatus);
	}


	@ExceptionHandler(UserValidation.class)
	public ResponseEntity<?> handleLoginException(UserValidation exception, HttpServletRequest request) {
		return handleBasicBusinessException(exception, request, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(InvalidParameterException.class)
	public ResponseEntity<?> handleUnprocessableEntityException(InvalidParameterException exception,
			HttpServletRequest request) {
		return handleBasicBusinessException(exception, request, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception exception, HttpServletRequest request) {
		return handleBasicBusinessException(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

