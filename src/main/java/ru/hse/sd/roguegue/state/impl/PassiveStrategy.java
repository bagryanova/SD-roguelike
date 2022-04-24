package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.state.MobStrategy;
import ru.hse.sd.roguegue.state.Position;

public class PassiveStrategy extends MobStrategy {
    public PassiveStrategy() {}

    /**
     * @param position doesn't update position.
     */
    @Override
    public Position getNewPosition(Position position) {
        return position;
    }
}
