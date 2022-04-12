package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.state.MobStrategy;
import ru.hse.sd.roguegue.state.Position;

public class PassiveStrategy extends MobStrategy {
    @Override
    public Position getNewPosition(Position position) {
        return position;
    }
}
