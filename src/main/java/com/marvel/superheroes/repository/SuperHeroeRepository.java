package com.marvel.superheroes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marvel.superheroes.model.SuperHeroeModel;

/**
 * Repository que implemneta la logica para el CRUD del superheroe
 * @author Tincho
 *
 */
@Repository
public interface SuperHeroeRepository  extends JpaRepository<SuperHeroeModel,Long>  {

	
	Optional<SuperHeroeModel> findById(Long id);
	
	List<SuperHeroeModel> findByName(String name);
	
	List<SuperHeroeModel> findByNameContaining(String name);
	
	
}
