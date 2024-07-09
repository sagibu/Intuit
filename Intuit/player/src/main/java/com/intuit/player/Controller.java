package com.intuit.player;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.intuit.player.service.PostgresPlayerService;

@RestController
@RequestMapping("/api/players")
public class Controller {
    private final PostgresPlayerService service;

    public Controller(PostgresPlayerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Player> getPlayers(@RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) throws Exception {
        return this.service.getPlayers(page, size);
    }

    @GetMapping("/{playerID}")
    public Player getPlayerByID(@PathVariable("playerID") String playerID) throws Exception {
        return this.service.getPlayerByID(playerID);
    }
}
