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
import com.intuit.player.database.InMemoryDB;
import com.intuit.player.service.InMemoryPlayerService;

public class InMemoryPlayerServiceTest {
    private InMemoryDB database;


    @BeforeEach
    public void setUp() {
        this.database = mock(InMemoryDB.class);

    }

    @Test
    public void testGetAllPlayersSuccesfully() throws Exception {
        List<Player> expectedPlayers = Arrays.asList(Player.builder().playerID("a").build(),
                Player.builder().playerID("b").build());
        when(this.database.getAllPlayers()).thenReturn(expectedPlayers);

        InMemoryPlayerService service = new InMemoryPlayerService(database);

        List<Player> actualPlayers = service.getPlayers();

        assertEquals(expectedPlayers, actualPlayers);
    }

    @Test
    public void testGetPlayerByIDSuccesfully() throws Exception {
        String playerID = "a";
        Player expectedPlayer = Player.builder().playerID(playerID).build();
        when(this.database.getPlayerByID(playerID)).thenReturn(Optional.of(expectedPlayer));

        InMemoryPlayerService service = new InMemoryPlayerService(database);

        Player actualPlayer = service.getPlayerByID(playerID);

        assertEquals(expectedPlayer, actualPlayer);
    }

    @Test
    public void testGetPlayerByNonExistingID() throws Exception {
        String playerID = "a";
        when(this.database.getPlayerByID(playerID)).thenReturn(Optional.empty());

        InMemoryPlayerService service = new InMemoryPlayerService(database);

        assertThrows(ServerWebInputException.class, () -> service.getPlayerByID(playerID));
    }
}
