import agh.ics.oop.OptionsParser;
import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionsParserTest {
    String[] args = {"f", "b", "l", "r", "xx"};
    MoveDirection[] res = {
            MoveDirection.FORWARD,
            MoveDirection.BACKWARD,
            MoveDirection.LEFT,
            MoveDirection.RIGHT,
    };

    @Test
    public void parse() {
        MoveDirection[] restest = OptionsParser.parse(args);
        assertEquals(restest.length, res.length);
        for(int i = 0; i < res.length; i++) {
            assertEquals(restest[i], res[i]);
        }
    }

}
