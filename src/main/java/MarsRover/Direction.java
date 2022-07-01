package MarsRover;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    static private final Direction[] values = values();

    public final Direction right() {
        return values[(ordinal() + 1) % values.length];
    }

    public final Direction left() {
        return values[(ordinal() - 1 + values.length) % values.length];
    }

    public final String toString() {
        return this.name().substring(0, 1);
    }
}
