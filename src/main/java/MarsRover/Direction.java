package MarsRover;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    static private final Direction[] values = values();

    public final Direction next() {
        return values[(ordinal() + 1) % values.length];
    }

    public final Direction prev() {
        return values[(ordinal() - 1 + values.length) % values.length];
    }
}
