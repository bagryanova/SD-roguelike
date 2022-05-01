package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.logic.Move;
import ru.hse.sd.roguegue.map.CellType;
import ru.hse.sd.roguegue.state.MobStrategy;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.status.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Mobs with this strategy can only move in a closed area.
 * Area is defined by initial position and maximum number of cells mob can step away from its initial position.
 */
public class AreaHoldingStrategy extends MobStrategy {
    public AreaHoldingStrategy() {
    }

    private final int maxDistance = 2;
    private boolean firstPosition = true;
    private Position initialPosition;

    /**
     * @param position update position, mob can only move in his area.
     */
    @Override
    public Position getNewPosition(Position position) {
        if (firstPosition) {
            firstPosition = false;
            initialPosition = position;
        }
        List<Move> moves = new ArrayList<>(List.of(Move.UP, Move.DOWN, Move.RIGHT, Move.LEFT));
        Collections.shuffle(moves);
        if (validatePosition(position.move(moves.get(0)))) {
            return position.move(moves.get(0));
        } else if (validatePosition(position.move(moves.get(1)))) {
            return position.move(moves.get(1));
        } else if (validatePosition(position.move(moves.get(2)))) {
            return position.move(moves.get(2));
        } else if (validatePosition(position.move(moves.get(3)))) {
            return position.move(moves.get(3));
        }
        return position;
    }

    /**
     * Checks if current cell is GROUND cell and if mob's distance from initial position is valid
     * @param position position to be checked
     * @return true if new position is valid
     */
    @Override
    public boolean validatePosition(Position position) {
        CellType[][] cells = Status.mapState.getMap().cellArray();
        final boolean isGround = cells[position.getY()][position.getX()] == CellType.GROUND;
        return isGround && checkDistance(position);
    }

    private boolean checkDistance(Position position) {
        return Math.abs(initialPosition.getX() - position.getX()) < maxDistance &&
                Math.abs(initialPosition.getY() - position.getY()) < maxDistance;
    }
}
