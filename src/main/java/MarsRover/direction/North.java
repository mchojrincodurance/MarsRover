package MarsRover.direction;

public class North implements Direction{

    @Override
    public Direction moveRight() {
        return new East();
    }

    @Override
    public Direction moveLeft() {
        return new West();
    }

    @Override
    public String toString() {
        return "N";
    }
}
