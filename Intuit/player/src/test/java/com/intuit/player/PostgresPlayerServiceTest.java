package com.intuit.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ServerWebInputException;
import com.intuit.player.database.PostgresDB;
import com.intuit.player.service.PostgresPlayerService;

public class PostgresPlayerServiceTest {
    private PostgresDB database;


    @BeforeEach
    public void setUp() {
        this.database = mock(PostgresDB.class);

    }

    @Test
    public void testGetAllPlayersSuccesfully() throws Exception {
        List<Player> expectedPlayers = Arrays.asList(Player.builder().playerID("a").build(),
                Player.builder().playerID("b").build());
        when(this.database.findAll()).thenReturn(expectedPlayers);

        PostgresPlayerService service = new PostgresPlayerService(this.database);

        List<Player> actualPlayers = service.getPlayers();

        assertEquals(expectedPlayers, actualPlayers);
    }

    @Test
    public void testGetPlayerByIDSuccesfully() throws Exception {
        String playerID = "a";
        Player expectedPlayer = Player.builder().playerID(playerID).build();
        when(this.database.findById(playerID)).thenReturn(Optional.of(expectedPlayer));

        PostgresPlayerService service = new PostgresPlayerService(database);

        Player actualPlayer = service.getPlayerByID(playerID);

        assertEquals(expectedPlayer, actualPlayer);
    }

    @Test
    public void testGetPlayerByNonExistingID() throws Exception {
        String playerID = "a";
        when(this.database.findById(playerID)).thenReturn(Optional.empty());

        PostgresPlayerService service = new PostgresPlayerService(database);

        assertThrows(ServerWebInputException.class, () -> service.getPlayerByID(playerID));
    }
}
