package com.intuit.player.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ServerWebInputException;
import com.intuit.player.Player;
import com.intuit.player.database.Database;
import com.intuit.player.database.InMemoryDB;

@Service
public class InMemoryPlayerService implements PlayerService {
    private final Database database;

    public InMemoryPlayerService(InMemoryDB database) {
        this.database = database;
    }

    public List<Player> getPlayers() {
        return this.database.getAllPlayers();
    }

    public Player getPlayerByID(@PathVariable String playerID) throws ServerWebInputException {
        return this.database.getPlayerByID(playerID).orElseThrow(() -> new ServerWebInputException(
                "Player with playerID %s does not exist".formatted(playerID)));
    }
}
