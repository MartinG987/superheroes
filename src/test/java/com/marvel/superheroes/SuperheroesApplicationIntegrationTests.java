package com.marvel.superheroes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Test de integracion de /getToken
 * @author Tincho
 *
 */
@SpringBootTest
@ActiveProfiles("dev")
public class SuperheroesApplicationIntegrationTests {

	@Autowired
	protected WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	
	private String token;
	
	@Value("${test.username.token}")
	String usernameTest;
	
	@Value("${test.password.token}")
	String passwordTest;
	
	
	/**
	 * Test de obtencion de token
	 * @throws Exception
	 */
	@Test
	public void testGetToken() throws Exception {

		init(false);
		
		mockMvc.perform(post("/getToken")
				.contentType(MediaType.APPLICATION_JSON)
				.content(getJsonUserToken()))
		        .andExpect(status().isOk());
	}

	
	/**
	 * Test Agregar Superheroe
	 * @throws Exception
	 */
	@Test
	public void testAddSuperHeroe() throws Exception {

		init(true);
		
		mockMvc.perform(post("/api/save")
				.contentType(MediaType.APPLICATION_JSON)
				.header("authentication", token)
				.content(getJsonSuperheroe("Batman",53)))
				.andExpect(status().isCreated());
	}

	
	
	/**
	 * Test Buscar Todos los super heroes
	 * @throws Exception
	 */
	@Test
	public void testFindAll() throws Exception {

		init(true);
		
		mockMvc.perform(get("/api/findAll")
				.header("authentication", token))
				.andExpect(status().isOk());
	}
	
	
	/**
	 * Test Update Superheroe
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {

		init(true);

		saveOneSuperheroe();
		
		var superHeroes = mockMvc.perform(get("/api/findByName?name=Lo")
										.header("authentication", token))
										.andReturn();
		
		mockMvc.perform(post("/api/update")
				.contentType(MediaType.APPLICATION_JSON)
				.header("authentication", token)
				.content(changeSuperheroe(superHeroes).toString()))
				.andExpect(status().isOk());
	}

	
	/**
	 * Test delete Superheroe
	 * @throws Exception
	 */
	@Test
	public void testDelete() throws Exception {

		init(true);

		saveOneSuperheroe();
		
		mockMvc.perform(delete("/api/delete/1")
				.contentType(MediaType.APPLICATION_JSON)
				.header("authentication", token))
				.andExpect(status().isNoContent());
	}
	
	
	
	/**
	 * Init
	 * @param token 
	 * @throws Exception 
	 * @throws JSONException 
	 * @throws UnsupportedEncodingException 
	 */
	private void init(boolean token) throws UnsupportedEncodingException, JSONException, Exception {
		if(mockMvc == null)
			mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		
		if (token)
			getToken();
	}
	
	
	/**
	 * Cambio la edad al superheroe
	 * @param superHeroes
	 * @return
	 * @throws JSONException
	 * @throws UnsupportedEncodingException
	 */
	private JSONObject changeSuperheroe(MvcResult superHeroes) throws JSONException, UnsupportedEncodingException {
		var superHeroe =  new JSONArray(superHeroes.getResponse().getContentAsString()).getJSONObject(0);
		superHeroe.remove("age");
		superHeroe.put("age", 33);
		return superHeroe;
	}
	
	
	private String getJsonSuperheroe(String name , Integer age) throws JSONException, UnsupportedEncodingException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		jsonObject.put("age", age);
		return jsonObject.toString();
	}


	private void saveOneSuperheroe() throws JSONException, Exception, UnsupportedEncodingException {
		
		mockMvc.perform(post("/api/save")
				.contentType(MediaType.APPLICATION_JSON)
				.header("authentication", token)
				.content(getJsonSuperheroe("Lobezno", 53)));
	}
	
	private void getToken() throws Exception, JSONException, UnsupportedEncodingException {
		if (token == null) 
			this.token =  new JSONObject(mockMvc.perform(post("/getToken")
					.contentType(MediaType.APPLICATION_JSON)
					.content(getJsonUserToken())).andReturn().getResponse().getContentAsString()).getString("token");
		
	}
	
	
	private String getJsonUserToken() throws JSONException {
		return new JSONObject()
						.put("username", usernameTest)
						.put("password", passwordTest)
						.toString();
	}
	
	
	
	
}
