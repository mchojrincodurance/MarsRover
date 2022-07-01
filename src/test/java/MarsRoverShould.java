import MarsRover.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MarsRoverShould {

    @ParameterizedTest
    @MethodSource("providerGridWithObstacles")
    public void stop_when_bumping_into_obstacles(List obstaclePositions, String expectedOutput, String commands) {
        MarsRover marsRover = new MarsRover(new Grid(obstaclePositions));
        assertEquals(expectedOutput, marsRover.execute(commands));
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
        MarsRover marsRover = new MarsRover(new Grid());
        assertEquals(expectedOutput, marsRover.execute(commands));
    }

    public static Stream<Arguments> providerGridWithObstacles() {
        return Stream.of(
                Arguments.of(Arrays.asList(new Position(0, 3)), "2:1:E", "MRMM"),
                Arguments.of(Arrays.asList(new Position(0, 3)), "O:0:2:N", "MMMM")
        );
    }

    @Test
    public void stay_still_if_no_commands_are_issued()
    {
        MarsRover rover = new MarsRover(new Grid());
        assertEquals("0:0:N", rover.execute(""));
    }

    @ParameterizedTest
    @CsvSource({
            "0:1:N,M",
            "1:0:E,RM",
            "0:0:S,MRRM",
    })

    public void move_in_the_expected_direction(String expectedOutput, String commands)
    {
        MarsRover rover = new MarsRover(new Grid());
        assertEquals(expectedOutput, rover.execute(commands));
    }

    @ParameterizedTest
    @CsvSource({
            "0:0:E,R",
            "0:0:S,RR",
            "0:0:W,RRR",
            "0:0:N,RRRR",
    })

    public void rotate(String expectedOutput, String commands)
    {
        MarsRover rover = new MarsRover(new Grid());
        assertEquals(expectedOutput, rover.execute(commands));
    }
}