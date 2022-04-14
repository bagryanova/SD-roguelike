package ru.hse.sd.roguegue.state;

import ru.hse.sd.roguegue.logic.Move;

import java.util.Objects;

import static java.lang.Math.abs;

/**
 * Position on the two-dimensional map
 */
public class Position implements Cloneable{
    private int x;
    private int y;

    /**
     * Sets position to the position specified by coordinates
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Updates position according to the given coordinates
     *
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

    public Position move(Move move) {
        Position tmp = this.clone();
        switch (move) {
            case DOWN -> tmp.updatePosition(x, y - 1);
            case UP -> tmp.updatePosition(x, y + 1);
            case LEFT -> tmp.updatePosition(x - 1, y);
            case RIGHT -> tmp.updatePosition(x + 1, y);
        }
        return tmp;
    }

    public int distance(Position position) {
        return abs(x - position.getX()) + abs(y - position.getY());
    }

    @Override
    public Position clone() {
        return new Position(this.x, this.y);
    }
}
