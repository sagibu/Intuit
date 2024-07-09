package com.intuit.player.database;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.intuit.player.Player;
import jakarta.annotation.PostConstruct;

@Component
public class PostgresInitializer {
    @Value("${PLAYERS_FILE}")
    private String playersFile;

    private final PostgresDB database;

    @Autowired
    public PostgresInitializer(PostgresDB database) {
        this.database = database;
    }

    @PostConstruct
    @Transactional
    public void initializeData() {
        List<Player> players = PlayersCSVReader.readFile(this.playersFile);
        this.database.saveAll(players);
    }
}
