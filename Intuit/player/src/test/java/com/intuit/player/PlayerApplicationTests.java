package com.intuit.player;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;


@SpringBootTest(classes = PlayerApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {"PLAYERS_FILE = player.csv"})
class PlayerApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	private static PostgreSQLContainer postgreSQLContainer =
			new PostgreSQLContainer<>("postgres:16-alpine").withDatabaseName("players");

	@BeforeAll
	static void setUp() {
		postgreSQLContainer.start();
	}

	@AfterAll
	static void tearDown() {
		postgreSQLContainer.stop();
	}


	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
		registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
	}


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

	@Test
	void testGetPage() throws Exception {
		int size = 5;
		this.mockMvc.perform(get("/api/players?page=1&size={size}", size))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(5))
				.andExpect(jsonPath("$.[0].playerID").value("abadfe01"));
	}

	@Test
	void testGetPageExceedingMaxSize() throws Exception {
		int size = 1000;
		this.mockMvc.perform(get("/api/players?page=2000&size={size}", size))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(0));
	}
}
