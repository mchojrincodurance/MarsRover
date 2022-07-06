package MarsRover.direction;

public class South implements Direction {

    @Override
    public Direction moveRight() {
        return new West();
    }

    @Override
    public Direction moveLeft() {
        return new East();
    }

    @Override
    public String toString() {
        return "S";
    }
}
