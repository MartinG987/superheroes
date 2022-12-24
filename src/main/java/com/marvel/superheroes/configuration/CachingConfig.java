package com.marvel.superheroes.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

/**
 * Configuracion de Cache
 * @author Tincho
 *
 */
@Slf4j
@Configuration
@EnableCaching
@EnableScheduling
public class CachingConfig {

	public static final String SUPERHEROES = "superheroes";

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager(SUPERHEROES);

	}

	//Verificar tiempo de Cache que sea la correcta para el uso.
	@CacheEvict(allEntries = true, value = { SUPERHEROES })
	@Scheduled(fixedDelay = 10 * 60 * 100, initialDelay = 500)
	public void reportCacheEvict() {
		log.info("Clear Cache"); 
	}

}