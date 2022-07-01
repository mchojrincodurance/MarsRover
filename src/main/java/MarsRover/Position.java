package MarsRover;

public record Position(int x, int y) {

    public String toString() {
        return x + ":" + y;
    }

    public boolean equals(Position otherPosition) {
        return this.x == otherPosition.x && this.y == otherPosition.y;
    }
}
