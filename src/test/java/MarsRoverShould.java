import MarsRover.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MarsRoverShould {

    @ParameterizedTest
    @MethodSource("providerGridWithObstacles")
    public void stop_when_bumping_into_obstacles(ArrayList obstaclePositions, String expectedOutput, String commands) {
        MarsRover marsRover = new MarsRover(obstaclePositions);
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
        MarsRover marsRover = new MarsRover();
        assertEquals(expectedOutput, marsRover.execute(commands));
    }

    public static Stream<Arguments> providerGridWithObstacles() {
        ArrayList obstaclePositions = new ArrayList();
        obstaclePositions.add(new Position(0, 3));

        return Stream.of(
                Arguments.of(obstaclePositions, "2:1:E", "MRMM"),
                Arguments.of(obstaclePositions, "O:0:2:N", "MMMM")
        );
    }

    @Test
    public void stay_still_if_no_commands_are_issued()
    {
        MarsRover rover = new MarsRover();
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
        MarsRover rover = new MarsRover();
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
        MarsRover rover = new MarsRover();
        assertEquals(expectedOutput, rover.execute(commands));
    }
}