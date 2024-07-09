package com.intuit.player.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebInputException;
import com.intuit.player.Player;
import com.intuit.player.database.PostgresDB;

@Service
public class PostgresPlayerService implements PlayerService {
    private PostgresDB database;

    public PostgresPlayerService(PostgresDB database) {
        this.database = database;
    }

    @Override
    public List<Player> getPlayers() {
        return this.database.findAll();
    }

    @Override
    public Player getPlayerByID(String playerID) throws ServerWebInputException {
        return this.database.findById(playerID).orElseThrow(() -> new ServerWebInputException(
                "Player with playerID %s does not exist".formatted(playerID)));
    }
}
