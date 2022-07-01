package MarsRover;

public enum Direction {
    NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");

    private final String value;
    static private final Direction[] values = values();

    Direction(String value) {
        this.value = value;
    }

    public final Direction right() {
        return values[(ordinal() + 1) % values.length];
    }

    public final Direction left() {
        return values[(ordinal() - 1 + values.length) % values.length];
    }

    public final String toString() {
        return this.value;
    }
}
