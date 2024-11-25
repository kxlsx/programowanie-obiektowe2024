package agh.ics.oop.model;

import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

public class OptionsParserTest {
    String[] args = {"f", "b", "l", "r"};
    MoveDirection[] res = {
            MoveDirection.FORWARD,
            MoveDirection.BACKWARD,
            MoveDirection.LEFT,
            MoveDirection.RIGHT,
    };

    String[] args_incorrect = {"f", "b", "l", "r", "xx", "f"};

    @Test
    public void parse() {
        List<MoveDirection> resTest = OptionsParser.parse(args);
        assertEquals(resTest.size(), res.length);
        for(int i = 0; i < res.length; i++) {
            assertEquals(resTest.get(i), res[i]);
        }
    }

    @Test
    public void parse_incorrect() {
        assertThrows(
                IllegalArgumentException.class,
                () -> OptionsParser.parse(args_incorrect)
        );
    }

}
