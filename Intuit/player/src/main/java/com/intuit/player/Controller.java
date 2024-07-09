package com.intuit.player;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/players")
public class Controller {
    private final PlayerService service;

    public Controller(PlayerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Player> getPlayers() throws Exception {
        return this.service.getPlayers();
    }

    @GetMapping("/{playerID}")
    public Player getPlayerByID(@PathVariable("playerID") String playerID) throws Exception {
        return this.service.getPlayerByID(playerID);
    }
}
