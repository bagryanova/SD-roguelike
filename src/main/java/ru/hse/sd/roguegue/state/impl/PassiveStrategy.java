package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.state.MobStrategy;
import ru.hse.sd.roguegue.state.Position;

public class PassiveStrategy extends MobStrategy {
    public PassiveStrategy() {
        super.lives = 4;
        super.strength = 2;
    }

    @Override
    public Position getNewPosition(Position position) {
        return position;
    }
}
