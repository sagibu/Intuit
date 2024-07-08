package com.intuit.player;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.player.database.PlayersCSVReader;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/players")
    public List<Player> getPlayers() {
        return PlayersCSVReader.readFile("Intuit/player/src/main/resources/player.csv");
    }
}
