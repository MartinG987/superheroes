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
		var start = System.currentTimeMillis();
        var proceed = joinPoint.proceed();
        var executionTime = System.currentTimeMillis() - start;
        logear(joinPoint, executionTime);
        return proceed;
	}

	
	private void logear(ProceedingJoinPoint joinPoint, long executionTime) {
		log.info(getInfoLog(joinPoint, executionTime));
	}


	private String getInfoLog(ProceedingJoinPoint joinPoint, long executionTime) {
		return new StringBuilder().append("Log Duracion ==>> ")
								  .append(joinPoint.getSignature())
				                  .append(" - Demora en Ejecutar : ")
				                  .append(executionTime)
				                  .append(" ms ").toString();
	}
	
	
}
