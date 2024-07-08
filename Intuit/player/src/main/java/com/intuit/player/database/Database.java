package com.intuit.player.database;

import java.util.List;

import com.intuit.player.Player;

public interface Database {
    public List<Player> readAllPlayers();
    public Player readPlayerByID(String playerID);
}
