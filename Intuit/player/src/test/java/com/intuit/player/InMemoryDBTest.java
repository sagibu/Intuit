package com.intuit.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import com.intuit.player.database.InMemoryDB;
import com.intuit.player.database.PlayersCSVReader;

public class InMemoryDBTest {
    private List<Player> mockPlayers;
    private InMemoryDB database;

    @BeforeEach
    void setUp() {
        this.mockPlayers = Arrays.asList(Player.builder().playerID("a").build(),
                Player.builder().playerID("b").build());
        try (MockedStatic<PlayersCSVReader> csvReader =
                Mockito.mockStatic(PlayersCSVReader.class)) {
            csvReader.when(() -> PlayersCSVReader.readFile(anyString()))
                    .thenReturn(this.mockPlayers);

            this.database = new InMemoryDB("");
        }

    }

    @Test
    void testGetAllPlayersFromDB() {
        assertEquals(this.mockPlayers, database.getAllPlayers());
    }

    @Test
    void testGetPlayerByIDFromDB() {
        assertEquals(this.mockPlayers.getFirst(), database.getPlayerByID("a").get());
    }

    @Test
    void testGetNonExistingPlayerByIDFromDB() {
        assertTrue(database.getPlayerByID("c").isEmpty());
    }
}
