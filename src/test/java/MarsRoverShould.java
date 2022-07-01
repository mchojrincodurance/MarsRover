import MarsRover.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Executor;
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
    @MethodSource("providerWrapAroundCommands")
    public void wrap_around_when_reaching_the_end_of_the_grid(String expectedOutput, String commands) {
        MarsRover marsRover = new MarsRover(null);
        assertEquals(expectedOutput, marsRover.execute(commands));
    }

    private static Stream<Arguments> providerWrapAroundCommands() {
        return Stream.of(
                Arguments.of("0:0:E", "RMMMMMMMMMM"),
                Arguments.of("0:0:W", "LMMMMMMMMMM"),
                Arguments.of("0:0:S", "RRMMMMMMMMMM"),
                Arguments.of("0:1:N", "MMMMMMMMMMM"),
                Arguments.of("0:0:N", "MMMMMMMMMM")
        );
    }

    public static Stream<Arguments> providerGridWithObstacles() {
        ArrayList obstaclePositions = new ArrayList();
        obstaclePositions.add(new Position(0, 3));

        return Stream.of(
                Arguments.of(obstaclePositions, "2:1:E", "MRMM"),
                Arguments.of(obstaclePositions, "O:0:2:N", "MMMM")
        );
    }
}