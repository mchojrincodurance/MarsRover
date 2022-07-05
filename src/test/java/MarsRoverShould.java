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

    @ParameterizedTest
    @CsvSource({
            "0:0:E,RMMMMMMMMMM",
            "0:0:W,LMMMMMMMMMM",
            "0:0:S,RRMMMMMMMMMM",
            "0:1:N,MMMMMMMMMMM",
            "0:0:N,MMMMMMMMMM",
    })
    public void wrap_around_when_reaching_the_end_of_the_grid(String expectedOutput, String commands) {
        MarsRover marsRover = new MarsRover();
        assertEquals(expectedOutput, marsRover.execute(commands));
    }
}