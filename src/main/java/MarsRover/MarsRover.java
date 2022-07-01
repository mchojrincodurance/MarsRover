package MarsRover;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MarsRover {

    public static final String OUTPUT_SEPARATOR = ":";
    public static final String OBSTACLE_MARKER = "O";
    public static final char ACTION_MOVE = 'M';
    public static final char ACTION_TURN_LEFT = 'L';
    public static final char ACTION_TURN_RIGHT = 'R';
    public static final int LOWER_GRID_BOUND = 0;
    public static final int UPPER_GRID_BOUND = 9;
    private final Grid grid;
    private Position currentPosition = getInitialPosition();
    private Direction currentDirection = getInitialDirection();

    private final ArrayList obstaclePositions;

    public MarsRover(Grid grid) {
        this.grid = grid;

        obstaclePositions = new ArrayList();
    }

    public MarsRover(Grid grid, @NotNull ArrayList obstaclePositions) {
        this.grid = grid;
        this.obstaclePositions = obstaclePositions;
    }

    public String execute(String commands) {
        String prefix = "";

        try {
            tryToExecute(commands);
        } catch (ObstacleFoundException e) {
            prefix = OBSTACLE_MARKER + OUTPUT_SEPARATOR;
        }

        return prefix + currentPosition.toString() + OUTPUT_SEPARATOR + currentDirection;
    }

    private void tryToExecute(String commands) throws ObstacleFoundException {
        for (char command : commands.toCharArray()) {
            tryToExecute(command);
        }
    }

    @NotNull
    private Direction getInitialDirection() {

        return Direction.NORTH;
    }

    @NotNull
    private Position getInitialPosition() {

        return new Position(LOWER_GRID_BOUND, LOWER_GRID_BOUND);
    }


    private void tryToExecute(char command) throws ObstacleFoundException {
        switch (command) {
            case ACTION_MOVE -> move();
            case ACTION_TURN_LEFT -> turnLeft();
            case ACTION_TURN_RIGHT -> turnRight();
        }
    }

    private void turnRight() {
        currentDirection = currentDirection.right();
    }

    private void turnLeft() {
        currentDirection = currentDirection.left();
    }

    private void move() throws ObstacleFoundException {
        Position nextPosition = grid.getNextPosition(currentPosition, currentDirection);

        if (grid.isObstacle(nextPosition)) {

            throw new ObstacleFoundException();
        }

        currentPosition = nextPosition;
    }

    public boolean isObstacle(Position newPosition) {

        return obstaclePositions.stream().anyMatch(
                item -> ((Position) item).equals(newPosition)
        );
    }

    private void moveWest() throws ObstacleFoundException {
        updatePosition(new Position(
                currentPosition.x() > LOWER_GRID_BOUND ? currentPosition.x() - 1 : UPPER_GRID_BOUND,
                currentPosition.y()));
    }

    private void updatePosition(Position newPosition) throws ObstacleFoundException {
        if (!isObstacle(newPosition)) {
            currentPosition = newPosition;
        } else {

            throw new ObstacleFoundException();
        }
    }

    private void moveEast() throws ObstacleFoundException {
        updatePosition(new Position(
                currentPosition.x() < UPPER_GRID_BOUND ? currentPosition.x() + 1 : LOWER_GRID_BOUND,
                currentPosition.y()));
    }

    private void moveSouth() throws ObstacleFoundException {
        updatePosition(new Position(
                currentPosition.x(),
                currentPosition.y() > LOWER_GRID_BOUND ? currentPosition.y() - 1 : UPPER_GRID_BOUND));
    }

    private void moveNorth() throws ObstacleFoundException {
        updatePosition(new Position(
                currentPosition.x(),
                currentPosition.y() < UPPER_GRID_BOUND ? currentPosition.y() + 1 : LOWER_GRID_BOUND));
    }
}
