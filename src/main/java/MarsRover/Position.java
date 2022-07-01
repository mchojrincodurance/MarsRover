package MarsRover;

public final class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public String toString()
    {
        return x + ":" + y;
    }

    public boolean equals(Position otherPosition)
    {
        return this.x == otherPosition.x && this.y == otherPosition.y;
    }
}
