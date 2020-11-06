package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
@SpringBootTest
class CharacterControllerTest {

	private MockMvc mvc;

	@Autowired
	private WebApplicationContext wav;

	//一覧
	@Test
	void testCharacterAll() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(wav).build();
		RequestBuilder request = get("http://localhost:8080/characterAll");
		mvc.perform(request).andExpect(status().isOk());
	}

	@Test
	void testCharacterDelete() {
	}

	@Test
	void testCharacterAdd() {
	}

	@Test
	void testSaveCharacter() {
	}

	@Test
	void testCharacterEdit() {
	}

	@Test
	void testEditChatacter() {
	}

	@Test
	void testBackCharacterAll() {
	}

}
