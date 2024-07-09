package com.intuit.player.database;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

import com.intuit.player.Player;

@Repository
public interface Database {
    public List<Player> getAllPlayers();

    public Optional<Player> getPlayerByID(String playerID);
}
