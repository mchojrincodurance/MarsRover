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

        return currentPosition.toString() + ":" + currentDirection;
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
        Position newPosition = currentPosition;

        switch (currentDirection) {
            case 'N':
                newPosition = new Position(
                        currentPosition.x(),
                        currentPosition.y() < 9 ? currentPosition.y() + 1 : 0);
                break;
            case 'S':
                newPosition = new Position(
                        currentPosition.x(),
                        currentPosition.y() > 0 ? currentPosition.y() - 1 : 9);
                break;
            case 'E':
                newPosition = new Position(
                        currentPosition.x() < 9 ? currentPosition.x() + 1 : 0,
                        currentPosition.y());
                break;
            case 'W':
                newPosition = new Position(
                        currentPosition.x() > 0 ? currentPosition.x() - 1 : 9,
                        currentPosition.y());
                break;
        }

        currentPosition = newPosition;
    }
}
