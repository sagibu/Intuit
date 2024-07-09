package com.intuit.player.service;

import java.util.List;
import com.intuit.player.Player;

public interface PlayerService {
    public List<Player> getPlayers();

    public Player getPlayerByID(String playerID);
}
