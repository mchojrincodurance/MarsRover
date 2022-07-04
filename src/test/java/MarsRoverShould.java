import MarsRover.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class MarsRoverShould {

    @ParameterizedTest
    @CsvSource({
            "0:1:N,M",
            "0:2:N,MM",
    })
    public void move_in_the_indicated_direction(String endPosition, String commands)
    {
        MarsRover rover = new MarsRover();

        assertEquals(endPosition, rover.execute(commands));
    }

    @ParameterizedTest
    @CsvSource({
            "0:9:S,RRM",
            "0:0:W,L",
            "0:0:S,LL",
            "0:0:E,LLL",
            "0:0:E,R",
    })
    public void rotate(String endPosition, String commands)
    {
        MarsRover rover = new MarsRover();

        assertEquals(endPosition, rover.execute(commands));
    }
}