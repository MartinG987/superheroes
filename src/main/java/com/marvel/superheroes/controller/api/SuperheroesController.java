package com.marvel.superheroes.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marvel.superheroes.anotation.Traceable;
import com.marvel.superheroes.bean.SuperHeroe;
import com.marvel.superheroes.controller.BaseController;
import com.marvel.superheroes.service.SuperHeroeService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller para CRUD de superheroes
 * @author Tincho
 *
 */
@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "SuperHeroes", description = "Funcionalidad crud de superheroes")
public class SuperheroesController extends BaseController{
	
	@Autowired
	SuperHeroeService superHeroeService;
	
	
	@GetMapping("/findAll")
	@Traceable
	public ResponseEntity<List<SuperHeroe>> getAll() {
		return new ResponseEntity<>(superHeroeService.findAll(), HttpStatus.OK);
	}

	
	@GetMapping("/findById/{id}")
	@Traceable
	public ResponseEntity<SuperHeroe> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(superHeroeService.findById(id), HttpStatus.OK);
	}

	
	@GetMapping("/findByName")
	@Traceable
	public ResponseEntity<List<SuperHeroe>> getByName(@RequestParam(required = true, name = "name") String name) {
		return new ResponseEntity<>(superHeroeService.findByName(name), HttpStatus.OK);
	}
	
	
	@PostMapping("/update")
	@Traceable
	public ResponseEntity<Object> update(@RequestBody SuperHeroe superHeroe) {
	   superHeroeService.update(superHeroe);
	   return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@PostMapping("/save")
	@Traceable
	public ResponseEntity<Object> save(@RequestBody SuperHeroe superHeroe) {
	   superHeroeService.save(superHeroe);
	   return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/delete/{id}")
	@Traceable
	public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
		superHeroeService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
