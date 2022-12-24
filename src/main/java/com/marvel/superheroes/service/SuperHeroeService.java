package com.marvel.superheroes.service;

import java.util.List;

import com.marvel.superheroes.bean.SuperHeroe;

/**
 * Service para implementar la logica del CRUD desuperheroes
 * @author Tincho
 *
 */
public interface SuperHeroeService {

	List<SuperHeroe> findAll();

	SuperHeroe findById(Long id);

	List<SuperHeroe> findByName(String name);

	void deleteById(Long id);

	void update(SuperHeroe superHeroe);

	void save(SuperHeroe superHeroe);

}
