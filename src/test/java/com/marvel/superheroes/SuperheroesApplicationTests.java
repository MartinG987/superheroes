package com.marvel.superheroes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.marvel.superheroes.model.SuperHeroeModel;
import com.marvel.superheroes.repository.SuperHeroeRepository;

/**
 * Test Unitarios de CRUD Superheroes
 * @author Tincho
 *
 */
@SpringBootTest
@ActiveProfiles("dev")
class SuperheroesApplicationTests {

	
	@Value("${entorno}")
	String entorno;

	@Autowired
	private SuperHeroeRepository superHeroeRepository;
	 
	/**
	 * Test Basico de Startup 
	 */
	@Test
	void testStartUp() {
		assertThat(entorno).isEqualTo("dev");
	}

	
	/**
	 * Test Add SuperHeroe
	 */
	@Test
	public void testAddSuperHeroe() {
		saveSuperHeroe("Tom" , 25);
		List<SuperHeroeModel> superHeroeByName = superHeroeRepository.findByName("Tom");
		assertEquals(1, superHeroeByName.size());
	}
	
	
	/**
	 * Test Find All SuperHeroe
	 */
	@Test
	public void testFindAllSuperHeroe() {
		saveSuperHeroe("Gregor" , 45);
		saveSuperHeroe("IronMan" , 15);
		saveSuperHeroe("Thor" , 10);

		List<SuperHeroeModel> superHeroes = superHeroeRepository.findAll();

		assertEquals(3, superHeroes.size());
	}
	
	
	/**
	 * Test Find By Name Like
	 */
	@Test
	public void testAddSuperHeroeAndGetByLike() {
		saveSuperHeroe("SuperMan" , 25);
		List<SuperHeroeModel> superHeroeByName = superHeroeRepository.findByNameContaining("uper");
		assertEquals(1, superHeroeByName.size());
	}
	
	
	/**
	 * Test Modificar SuperHeroe
	 */
	@Test
	public void testUpdateSuperHeroe() {
		saveSuperHeroe("SpiderMan" , 45);
		SuperHeroeModel superHeroe = superHeroeRepository.findByName("SpiderMan").get(0);
		superHeroe.setAge(50);
		superHeroeRepository.save(superHeroe);
		superHeroe = superHeroeRepository.findByName("SpiderMan").get(0);
		assertEquals(50, superHeroe.getAge());
	}

	
	/**
	 * Test Delete Super Heroe
	 */
	@Test
	public void testDeleteSuperHeroe() {
		saveSuperHeroe("SpiderMan" , 45);
		SuperHeroeModel superHeroe = superHeroeRepository.findByName("SpiderMan").get(0);
		superHeroeRepository.delete(superHeroe);
		List<SuperHeroeModel> superHeroes = superHeroeRepository.findByName("SpiderMan");
		assertEquals(0, superHeroes.size());
	}
	
	
	/**
	 * Aux Method
	 * @param name
	 * @param age
	 */
	private void saveSuperHeroe(String name, int age) {
		SuperHeroeModel superHeroe = new SuperHeroeModel();
		superHeroe.setName(name);
		superHeroe.setAge(age);
		superHeroeRepository.save(superHeroe);
	}

}
