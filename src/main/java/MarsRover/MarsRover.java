package MarsRover;

public class MarsRover {

    private Position currentPosition;

    public MarsRover(Grid grid1) {
        currentPosition = new Position(0, 0, 'N');
    }

    public String execute(String commands) {

        for (char command : commands.toCharArray()) {
            executeCommand(command);
        }

        return currentPosition.toString();
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
        char newDirection = 'N';

        switch (currentPosition.direction()) {
            case 'N':
                newDirection = 'E';
                break;
            case 'E':
                newDirection = 'S';
                break;
            case 'S':
                newDirection = 'W';
                break;
            case 'W':
                newDirection = 'N';
                break;
        }

        currentPosition = new Position(currentPosition.x(), currentPosition.y(), newDirection);
    }

    private void turnLeft() {
        char newDirection = 'N';

        switch (currentPosition.direction()) {
            case 'N':
                newDirection = 'W';
                break;
            case 'W':
                newDirection = 'S';
                break;
            case 'S':
                newDirection = 'E';
                break;
            case 'E':
                newDirection = 'N';
                break;
        }

        currentPosition = new Position(currentPosition.x(), currentPosition.y(), newDirection);
    }

    private void move() {
        switch (currentPosition.direction()) {
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
        currentPosition = new Position(
                currentPosition.x() > 0 ? currentPosition.x() - 1 : 9,
                currentPosition.y(),
                currentPosition.direction());
    }

    private void moveEast() {
        currentPosition = new Position(
                currentPosition.x() < 9 ? currentPosition.x() + 1 : 0,
                currentPosition.y(),
                currentPosition.direction());
    }

    private void moveSouth() {
        currentPosition = new Position(
                currentPosition.x(),
                currentPosition.y() > 0 ? currentPosition.y() - 1 : 9,
                currentPosition.direction());
    }

    private void moveNorth() {
        currentPosition = new Position(
                currentPosition.x(),
                currentPosition.y() < 9 ? currentPosition.y() + 1 : 0,
                currentPosition.direction());
    }
}
