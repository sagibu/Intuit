package com.intuit.player.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.intuit.player.Player;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class InMemoryDB implements Database {

    private final Map<String, Player> players;

    public InMemoryDB(@Value("${PLAYERS_FILE}") String filePath) throws IllegalArgumentException {
        List<Player> playersList = PlayersCSVReader.readFile(filePath);
        // TODO: in case of duplicate rows, currently returning the first, should raise an error
        BinaryOperator<Player> duplicate_handler = BinaryOperator.maxBy((a, b) -> 0);
        this.players = playersList.stream().collect(Collectors.toMap(Player::getPlayerID,
                Function.identity(), duplicate_handler, TreeMap::new));
    }


    @Override
    public List<Player> getAllPlayers() {
        log.debug("Fetching All Players from DB");
        return new ArrayList<>(this.players.values());
    }

    @Override
    public Optional<Player> getPlayerByID(String playerID) {
        log.debug("Fetching Player %s from DB", playerID);
        return Optional.ofNullable(this.players.get(playerID));
    }

}
