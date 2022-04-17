package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.logic.Move;
import ru.hse.sd.roguegue.state.MobStrategy;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.status.Status;

public class AggressiveStrategy extends MobStrategy {
    public AggressiveStrategy() {
        super.lives = 3;
        super.strength = 6;
    }

    /**
     * @param position update position according to the user position, try to be closer.
     */
    @Override
    public Position getNewPosition(Position position) {
        Position userPosition = Status.userState.getPosition();
        if (super.validatePosition(position.move(Move.UP)) && (position.move(Move.UP).distance(userPosition) < userPosition.distance(position))) {
            return position.move(Move.UP);
        } else if (super.validatePosition(position.move(Move.DOWN)) && (position.move(Move.DOWN).distance(userPosition) < userPosition.distance(position))) {
            return position.move(Move.DOWN);
        } else if (super.validatePosition(position.move(Move.LEFT)) && (position.move(Move.LEFT).distance(userPosition) < userPosition.distance(position))) {
            return position.move(Move.LEFT);
        } else if (super.validatePosition(position.move(Move.RIGHT)) && (position.move(Move.RIGHT).distance(userPosition) < userPosition.distance(position))) {
            return position.move(Move.RIGHT);
        }
        return position;
    }
}
