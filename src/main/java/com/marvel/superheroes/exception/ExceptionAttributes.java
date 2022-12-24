package com.marvel.superheroes.exception;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

/**
 * Manejo de Atributos para Excepciones
 * @author Tincho
 *
 */
public interface ExceptionAttributes {

	Map<String, Object> getExceptionAttributes(Exception exception, HttpServletRequest httpRequest,
			HttpStatus httpStatus);

}
