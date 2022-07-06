package MarsRover;

import MarsRover.direction.Direction;
import MarsRover.direction.North;

import java.util.ArrayList;

public class MarsRover {

    private Position currentPosition;
    private char currentDirection;

    private Direction direction = new North();

    private ArrayList obstaclePositions;

    public MarsRover(ArrayList obstaclePositions) {
        currentPosition = new Position(0, 0);
        currentDirection = 'N';

        this.obstaclePositions = obstaclePositions != null ? obstaclePositions : new ArrayList();
    }

    public String execute(String commands) {
        try {
            for (char command : commands.toCharArray()) {
                executeCommand(command);
            }

            return currentPosition.toString() + ":" + direction;
        } catch (ObstacleFoundException e) {

            return "O:" + currentPosition.toString() + ":" + direction;
        }
    }

    private void executeCommand(char command) throws ObstacleFoundException {
        switch (command) {
            case 'M':
                move();
                break;
            case 'L':
                direction = direction.moveLeft();
                break;
            case 'R':
                direction = direction.moveRight();
                break;
        }
    }

    private void move() throws ObstacleFoundException {
        switch (currentDirection) {
            case 'N':
                moveNorth();
                break;
            case 'S':
                moveSouth();
                break;
            case 'E':
                moveEast();
                break;
            case 'W':
                moveWest();
                break;
        }
    }

    private boolean isObstacle(Position newPosition) {

        boolean b = obstaclePositions.stream().anyMatch(
                item -> ((Position)item).equals(newPosition)
        );

        return b;
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
