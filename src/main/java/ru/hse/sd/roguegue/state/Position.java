package ru.hse.sd.roguegue.state;

import java.util.Objects;

/**
 * Position on the two-dimensional map
 */
public class Position {
    private int x;
    private int y;

    /**
     * Sets position to the position specified by coordinates
     * @param x x coordinate
     * @param y y coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     Updates position according to the given coordinates
     * @param x x coordinate
     * @param y y coordinate
     */
    public void updatePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return current x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * @return current y coordinate
     */
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return getX() == position.getX() && getY() == position.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
