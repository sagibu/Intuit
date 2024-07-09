package com.intuit.player.csv_reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.intuit.player.Player;
import com.intuit.player.database.PlayersCSVReader;

public class PlayerCSVReaderTest {
    @Test
    public void testSuccesfulReadValidSample() {
        List<Player> players = PlayersCSVReader.readFile("csv_reader/valid_sample.csv");
        assertEquals(3, players.size());
    }

    // TODO: make this test pass
    // @Test
    // public void testRaiseErrorWithBadColumns() {
    // assertThrows(IllegalArgumentException.class, () -> PlayersCSVReader
    // .readFile("csv_reader/bad_columns.csv"));
    // }

    @Test
    public void testRaiseErrorWithBadValues() {
        assertThrows(IllegalArgumentException.class,
                () -> PlayersCSVReader.readFile("csv_reader/bad_values.csv"));
    }


}
