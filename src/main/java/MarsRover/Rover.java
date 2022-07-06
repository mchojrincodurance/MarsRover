package MarsRover;

import MarsRover.direction.Direction;
import MarsRover.direction.North;

public class Rover {

    private Direction direction = new North();

    private String currentDirection;

    private Position currentPosition;

    public Rover() {
        currentPosition = new Position(0, 0);
        currentDirection = new North().toString();
    }

    public String execute(String commands) {
        try {
            for (char command : commands.toCharArray()) {
                executeCommand(command);
            }

            return currentPosition.toString() + ":" + currentDirection;
        } catch (ObstacleFoundException e) {

            return "O:" + currentPosition.toString() + ":" + currentDirection;
        }
    }

    private String executeCommand(char command) throws ObstacleFoundException {
        switch (command) {
            case 'M':
                //move();
                break;
            case 'L':
                direction = direction.moveLeft();
                break;
            case 'R':
                direction = direction.moveRight();
                break;
        }
        return direction.toString();
    }
}
