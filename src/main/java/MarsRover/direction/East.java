package MarsRover.direction;

public class East implements Direction{

    @Override
    public Direction moveRight() {
        return new South();
    }

    @Override
    public Direction moveLeft() {
        return new North();
    }

    @Override
    public String toString() {
        return "E";
    }
}
