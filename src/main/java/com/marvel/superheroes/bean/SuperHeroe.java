package com.marvel.superheroes.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

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
	@NotEmpty
	private String name;
	@NotEmpty
	@Min(1)
	private Integer age;
	
	
	public SuperHeroe(SuperHeroeModel superHeroeModel) {
		this.id = superHeroeModel.getId();
		this.name = superHeroeModel.getName();
		this.age = superHeroeModel.getAge();
	}

}
