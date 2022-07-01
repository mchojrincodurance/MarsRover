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
    private Position currentPosition;
    private Direction currentDirection;

    private final ArrayList obstaclePositions;

    public MarsRover(ArrayList obstaclePositions) {
        currentPosition = getInitialPosition();
        currentDirection = getInitialDirection();

        this.obstaclePositions = obstaclePositions != null ? obstaclePositions : new ArrayList();
    }

    public String execute(String commands) {
        try {
            tryToExecute(commands);
        } catch (ObstacleFoundException e) {

            return OBSTACLE_MARKER + OUTPUT_SEPARATOR + currentPosition.toString() + OUTPUT_SEPARATOR + currentDirection;
        }
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
        currentDirection = currentDirection.next();
    }

    private void turnLeft() {
        currentDirection = currentDirection.prev();
    }

    private void move() throws ObstacleFoundException {
        switch (currentDirection) {
            case NORTH -> moveNorth();
            case SOUTH -> moveSouth();
            case EAST -> moveEast();
            case WEST -> moveWest();
        }
    }

    private boolean isObstacle(Position newPosition) {

        return obstaclePositions.stream().anyMatch(
                item -> ((Position)item).equals(newPosition)
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
