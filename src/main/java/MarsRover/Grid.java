package MarsRover;

import java.util.Collections;
import java.util.List;

public class Grid {
    public static final int MAX_ROWS = 10;
    public static final int MAX_COLS = 10;

    private int rows = MAX_ROWS;
    private int cols = MAX_COLS;

    private List obstaclePositions = Collections.emptyList();

    public Grid()
    {
    }

    Grid(int rows, int cols)
    {
        this.rows = rows;
        this.cols = cols;
    }

    Grid(List obstaclePositions)
    {
        this.obstaclePositions = obstaclePositions;
    }

    Grid(int rows, int cols, List obstaclePositions) {
        this.rows = rows;
        this.cols = cols;
        this.obstaclePositions = obstaclePositions;
    }

    public boolean isObstacle(Position newPosition) {

        return obstaclePositions.stream().anyMatch(
                item -> ((Position) item).equals(newPosition)
        );
    }

    public Position getNextPosition(Position current, Direction direction) {
        int x = current.x();
        int y = current.y();

        switch (direction) {
            case NORTH -> y = (y + 1) % rows;
            case SOUTH -> y = y > 0 ? y - 1 : rows - 1;
            case WEST -> x = x > 0 ? x - 1 : cols - 1;
            case EAST -> x = (x + 1) % cols;
        }

        return new Position(x, y);
    }
}
