package com.marvel.superheroes.anotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Custom Annotation para medir el tiempo de respuesta
 * @author Tincho
 *
 */
@Slf4j
@Aspect
@Component
public class TraceableAspect {

	
	@Around("@annotation(Traceable)")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info("Log Duracion ==>> "+ joinPoint.getSignature() + "  - Demora en Ejecutar : " + executionTime + "ms");
        return proceed;
	}
	
	
}
