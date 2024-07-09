package com.intuit.player.service;

import java.util.List;
import java.util.Optional;
import com.intuit.player.Player;

public interface PlayerService {
    public List<Player> getPlayers(Optional<Integer> page, Optional<Integer> size);

    public Player getPlayerByID(String playerID);
}
