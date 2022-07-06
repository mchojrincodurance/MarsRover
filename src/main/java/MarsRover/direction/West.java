package MarsRover.direction;

public class West implements Direction{

    @Override
    public Direction moveRight() {
        return new North();
    }

    @Override
    public Direction moveLeft() {
        return new South();
    }

    @Override
    public String toString() {
        return "W";
    }
}
