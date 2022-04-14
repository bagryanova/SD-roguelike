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

    @Override
    public Position getNewPosition(Position position) {
        Position userPosition = Status.userState.getPosition();
        if (super.validatePosition(userPosition.move(Move.UP)) && (userPosition.move(Move.UP).distance(position) < userPosition.distance(position))) {
            return userPosition.move(Move.UP);
        } else if (super.validatePosition(userPosition.move(Move.DOWN)) && (userPosition.move(Move.DOWN).distance(position) < userPosition.distance(position))) {
            return userPosition.move(Move.DOWN);
        } else if (super.validatePosition(userPosition.move(Move.LEFT)) && (userPosition.move(Move.LEFT).distance(position) < userPosition.distance(position))) {
            return userPosition.move(Move.LEFT);
        } else if (super.validatePosition(userPosition.move(Move.RIGHT)) && (userPosition.move(Move.RIGHT).distance(position) < userPosition.distance(position))) {
            return userPosition.move(Move.RIGHT);
        }
        return position;
    }
}
