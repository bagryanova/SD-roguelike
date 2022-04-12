package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.logic.Move;
import ru.hse.sd.roguegue.state.MobStrategy;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.status.Status;

public class AvoidingStrategy extends MobStrategy {
    @Override
    public Position getNewPosition(Position position) {
        Position userPosition = Status.userState.getPosition();
        if (userPosition.move(Move.UP).distance(position) > userPosition.distance(position)) {
            return userPosition.move(Move.UP);
        } else if (userPosition.move(Move.DOWN).distance(position) > userPosition.distance(position)) {
            return userPosition.move(Move.DOWN);
        } else if (userPosition.move(Move.LEFT).distance(position) > userPosition.distance(position)) {
            return userPosition.move(Move.LEFT);
        } else if (userPosition.move(Move.RIGHT).distance(position) > userPosition.distance(position)) {
            return userPosition.move(Move.RIGHT);
        }
        return position;
    }
}
