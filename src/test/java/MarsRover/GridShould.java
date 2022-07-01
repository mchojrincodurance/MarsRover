package MarsRover;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GridShould {

    @ParameterizedTest
    @CsvSource({
            "0:0,NORTH,0:1",
            "0:0,SOUTH,0:9",
            "0:0,EAST,1:0",
            "0:0,WEST,9:0",
    })
    public void calculate_next_position(String currentPosition, String direction, String resultingPosition)
    {
        Grid grid = new Grid();

        assertEquals(
                new Position(Integer.valueOf(resultingPosition.substring(0, 1)), Integer.valueOf(resultingPosition.substring(2, 3))),
                grid.getNextPosition(
                        new Position(Integer.valueOf(currentPosition.substring(0, 1)), Integer.valueOf(currentPosition.substring(2, 3))),
                        Direction.valueOf(direction))
        );
    }
}