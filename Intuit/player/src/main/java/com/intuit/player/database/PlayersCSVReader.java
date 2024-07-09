package com.intuit.player.database;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.intuit.player.Player;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PlayersCSVReader {
    public static List<Player> buildPlayersList(Path path) throws IllegalArgumentException {
        try (Reader reader = Files.newBufferedReader(path)) {
            CsvToBean<Player> builder =
                    new CsvToBeanBuilder<Player>(reader).withType(Player.class).build();

            return builder.parse();
        } catch (Exception e) {
            String errorMessage = "File in %s could not be parsed with the error: %s"
                    .formatted(path, e.getMessage());
            log.error(errorMessage, e);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static List<Player> readFile(String filepath) throws IllegalArgumentException {
        return PlayersCSVReader.buildPlayersList(Paths.get(filepath));
    }
}
