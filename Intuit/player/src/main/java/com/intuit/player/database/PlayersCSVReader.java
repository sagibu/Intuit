package com.intuit.player.database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.List;

import com.intuit.player.Player;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PlayersCSVReader {
    public static List<Player> readFile(String resourcePath) throws IllegalArgumentException {
        URL url = PlayersCSVReader.class.getClassLoader().getResource(resourcePath);
        try (Reader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            CsvToBean<Player> builder =
                    new CsvToBeanBuilder<Player>(reader).withType(Player.class).build();

            return builder.parse();
        } catch (Exception e) {
            String errorMessage = "File in %s could not be parsed with the error: %s"
                    .formatted(resourcePath, e.getMessage());
            log.error(errorMessage, e);
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
