package com.intuit.player;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"PLAYERS_FILE = src/main/resources/player.csv"})
class PlayerApplicationTests {
	@Autowired
	private MockMvc mockMvc;


	@Test
	void contextLoads() {}

	@Test
	void testGetAllPlayers() throws Exception {
		this.mockMvc.perform(get("/api/players")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(19370))
				.andExpect(jsonPath("$.[0].playerID").value("aardsda01"));
	}

	@Test
	void testGetPlayerByID() throws Exception {
		String playerID = "owenfr01";
		this.mockMvc.perform(get("/api/players/%s".formatted(playerID))).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.playerID").value(playerID));
	}

	@Test
	void testGetPlayerByIDReturns400WhenNotExists() throws Exception {
		String playerID = "a";
		this.mockMvc.perform(get("/api/players/%s".formatted(playerID)))
				.andExpect(status().is4xxClientError());
	}


}
