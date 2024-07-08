package com.intuit.player.database;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;

import com.intuit.player.Player;
import com.opencsv.CSVReader;

public class PlayersCSVReader {

    public static Optional<Integer> strToInt(String str) {
        return Optional.ofNullable(Strings.trimToNull(str)).map(s -> Integer.valueOf(s));
    }

    public static List<Player> readFile(String filepath) {
        try (CSVReader reader = new CSVReader(new FileReader(filepath))) {
            List<Player> records = new ArrayList<>();
            String[] line;

            // Read header separately
            String[] headers = reader.readNext();

            while ((line = reader.readNext()) != null) {
                Player record = new Player(line[0], PlayersCSVReader.strToInt(line[1]),
                        PlayersCSVReader.strToInt(line[2]), PlayersCSVReader.strToInt(line[3]),
                        line[4], line[5], line[6]);
                records.add(record);
            }
            return records;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
