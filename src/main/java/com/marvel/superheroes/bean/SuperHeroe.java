package com.marvel.superheroes.bean;

import com.marvel.superheroes.model.SuperHeroeModel;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Bean de SuperHeroe
 * @author Tincho
 *
 */
@Data
@AllArgsConstructor
public class SuperHeroe {
	
	private Long id;
	private String name;
	private Integer age;
	
	
	public SuperHeroe(SuperHeroeModel superHeroeModel) {
		this.id = superHeroeModel.getId();
		this.name = superHeroeModel.getName();
		this.age = superHeroeModel.getAge();
	}

}
