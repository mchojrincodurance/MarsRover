package MarsRover;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MarsRover {

    public static final String OUTPUT_SEPARATOR = ":";
    public static final String OBSTACLE_MARKER = "O";
    public static final char ACTION_MOVE = 'M';
    public static final char ACTION_TURN_LEFT = 'L';
    public static final char ACTION_TURN_RIGHT = 'R';
    private Position currentPosition;
    private Direction currentDirection;

    private final ArrayList obstaclePositions;

    public MarsRover(ArrayList obstaclePositions) {
        currentPosition = getInitialPosition();
        currentDirection = getInitialDirection();

        this.obstaclePositions = obstaclePositions != null ? obstaclePositions : new ArrayList();
    }

    @NotNull
    private Direction getInitialDirection() {
        return Direction.NORTH;
    }

    @NotNull
    private Position getInitialPosition() {
        return new Position(0, 0);
    }

    public String execute(String commands) {
        try {
            for (char command : commands.toCharArray()) {
                executeCommand(command);
            }

            return currentPosition.toString() + OUTPUT_SEPARATOR + currentDirection;
        } catch (ObstacleFoundException e) {

            return OBSTACLE_MARKER + OUTPUT_SEPARATOR + currentPosition.toString() + OUTPUT_SEPARATOR + currentDirection;
        }
    }

    private void executeCommand(char command) throws ObstacleFoundException {
        switch (command) {
            case ACTION_MOVE:
                move();
                break;
            case ACTION_TURN_LEFT:
                turnLeft();
                break;
            case ACTION_TURN_RIGHT:
                turnRight();
                break;
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
            case NORTH:
                moveNorth();
                break;
            case SOUTH:
                moveSouth();
                break;
            case EAST:
                moveEast();
                break;
            case WEST:
                moveWest();
                break;
        }
    }

    private boolean isObstacle(Position newPosition) {

        return obstaclePositions.stream().anyMatch(
                item -> ((Position)item).equals(newPosition)
        );
    }

    private void moveWest() throws ObstacleFoundException {
        Position newPosition = new Position(
                currentPosition.x() > 0 ? currentPosition.x() - 1 : 9,
                currentPosition.y());

        if (!isObstacle(newPosition)) {
            currentPosition = newPosition;
        } else {

            throw new ObstacleFoundException();
        }
    }

    private void moveEast() throws ObstacleFoundException {
        Position newPosition = new Position(
                currentPosition.x() < 9 ? currentPosition.x() + 1 : 0,
                currentPosition.y());

        if (!isObstacle(newPosition)) {
            currentPosition = newPosition;
        } else {

            throw new ObstacleFoundException();
        }
    }

    private void moveSouth() throws ObstacleFoundException {
        Position newPosition = new Position(
                currentPosition.x(),
                currentPosition.y() > 0 ? currentPosition.y() - 1 : 9);

        if (!isObstacle(newPosition)) {
            currentPosition = newPosition;
        } else {

            throw new ObstacleFoundException();
        }
    }

    private void moveNorth() throws ObstacleFoundException {
        Position newPosition = new Position(
                currentPosition.x(),
                currentPosition.y() < 9 ? currentPosition.y() + 1 : 0);

        if (!isObstacle(newPosition)) {
            currentPosition = newPosition;
        } else {

            throw new ObstacleFoundException();
        }
    }
}
