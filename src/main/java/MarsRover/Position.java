package MarsRover;

public final class Position {
    private final int x;
    private final int y;
    private final char direction;

    public Position(int x, int y, char direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public char direction() {
        return direction;
    }

    public String toString()
    {
        return x + ":" + y + ":" + direction;
    }
}
