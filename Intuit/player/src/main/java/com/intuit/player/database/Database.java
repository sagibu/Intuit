package com.intuit.player.database;

import java.util.List;
import java.util.Optional;

import com.intuit.player.Player;

public interface Database {
    public List<Player> getAllPlayers();

    public Optional<Player> getPlayerByID(String playerID);
}
