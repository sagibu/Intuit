package com.intuit.player.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
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
    public List<Player> getPlayers(Optional<Integer> page, Optional<Integer> size)
            throws ServerWebInputException {
        if (page.isPresent() && size.isEmpty() || (page.isEmpty() && size.isPresent())) {
            throw new ServerWebInputException(
                    "page and size should either be used together or both of them not used");
        }

        if (page.isEmpty() && size.isEmpty())
            return this.database.findAll();

        return this.database.findAll(PageRequest.of(page.get(), size.get())).toList();
    }

    @Override
    public Player getPlayerByID(String playerID) throws ServerWebInputException {
        return this.database.findById(playerID).orElseThrow(() -> new ServerWebInputException(
                "Player with playerID %s does not exist".formatted(playerID)));
    }
}
