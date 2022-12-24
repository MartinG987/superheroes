package com.marvel.superheroes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.marvel.superheroes.bean.SuperHeroe;
import com.marvel.superheroes.model.SuperHeroeModel;
import com.marvel.superheroes.repository.SuperHeroeRepository;

/**
 * Implmentacion del Servicio de SuperHeroes
 * @author Tincho
 *
 */
@Service
public class SuperHeroeServiceImpl implements SuperHeroeService{

	
	@Autowired
	private SuperHeroeRepository superHeroeRepository;


	@Override
	@Cacheable("superheroes")
	public List<SuperHeroe> findAll() {
		return superHeroeRepository.findAll().stream()
											 .map(p -> new SuperHeroe(p))
											 .collect(Collectors.toList());
	}

	@Override
	public SuperHeroe findById(Long id) {
		return new SuperHeroe(superHeroeRepository.findById(id).get());
	}

	
	@Override
	public List<SuperHeroe> findByName(String name) {
		return superHeroeRepository.findByNameContaining(name).stream()
											 .map(p -> new SuperHeroe(p))
											 .collect(Collectors.toList());
	}

	@Override
	public void deleteById(Long id) {
		superHeroeRepository.deleteById(id);
		
	}

	
	@Override
	public void update(SuperHeroe superHeroe) {
		superHeroeRepository.save(new SuperHeroeModel(superHeroe.getId(), superHeroe.getName(),superHeroe.getAge()));
		
	}

	@Override
	public void save(SuperHeroe superHeroe) {
		superHeroeRepository.save(new SuperHeroeModel( superHeroe.getName(),superHeroe.getAge()));
	}

}
