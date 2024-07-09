package com.intuit.player.csv_reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.intuit.player.Player;
import com.intuit.player.database.PlayersCSVReader;

public class PlayerCSVReaderTest {
    private final String RESOURCE_FOLDER = "src/test/resources/csv_reader";


    @Test
    public void testSuccesfulReadValidSample() {
        List<Player> players =
                PlayersCSVReader.readFile("%s/valid_sample.csv".formatted(this.RESOURCE_FOLDER));
        assertEquals(3, players.size());
    }

    // TODO: make this test pass
    // @Test
    // public void testRaiseErrorWithBadColumns() {
    // assertThrows(IllegalArgumentException.class, () -> PlayersCSVReader
    // .readFile("%s/bad_columns.csv".formatted(this.RESOURCE_FOLDER)));
    // }

    @Test
    public void testRaiseErrorWithBadValues() {
        assertThrows(IllegalArgumentException.class, () -> PlayersCSVReader
                .readFile("%s/bad_values.csv".formatted(this.RESOURCE_FOLDER)));
    }


}
