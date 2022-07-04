package MarsRover;

public class MarsRover {

    private Position currentPosition;
    private char currentDirection;

    public MarsRover() {
        currentPosition = new Position(0, 0);
        currentDirection = 'N';
    }

    public String execute(String commands) {
        for (char command : commands.toCharArray()) {
            executeCommand(command);
        }

        return currentPosition.toString() + ":" + currentDirection;
    }

    private void executeCommand(char command) {
        switch (command) {
            case 'M':
                move();
                break;
            case 'L':
                turnLeft();
                break;
            case 'R':
                turnRight();
                break;
        }
    }

    private void turnRight() {
        switch (currentDirection) {
            case 'N':
                currentDirection = 'E';
                break;
            case 'E':
                currentDirection = 'S';
                break;
            case 'S':
                currentDirection = 'W';
                break;
            case 'W':
                currentDirection = 'N';
                break;
        }
    }

    private void turnLeft() {
        switch (currentDirection) {
            case 'N':
                currentDirection = 'W';
                break;
            case 'W':
                currentDirection = 'S';
                break;
            case 'S':
                currentDirection = 'E';
                break;
            case 'E':
                currentDirection = 'N';
                break;
        }
    }

    private void move() {
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

    private void moveWest() {
        Position newPosition = new Position(
                currentPosition.x() > 0 ? currentPosition.x() - 1 : 9,
                currentPosition.y());

        currentPosition = newPosition;
    }

    private void moveEast() {
        Position newPosition = new Position(
                currentPosition.x() < 9 ? currentPosition.x() + 1 : 0,
                currentPosition.y());

        currentPosition = newPosition;
    }

    private void moveSouth() {
        Position newPosition = new Position(
                currentPosition.x(),
                currentPosition.y() > 0 ? currentPosition.y() - 1 : 9);

        currentPosition = newPosition;
    }

    private void moveNorth() {
        Position newPosition = new Position(
                currentPosition.x(),
                currentPosition.y() < 9 ? currentPosition.y() + 1 : 0);

        currentPosition = newPosition;
    }
}
