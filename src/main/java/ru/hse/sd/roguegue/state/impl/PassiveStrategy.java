package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.state.MobStrategy;
import ru.hse.sd.roguegue.state.Position;

public class PassiveStrategy extends MobStrategy {
    PassiveStrategy() {
        super.lives = 10;
        super.strength = 0;
    }

    @Override
    public Position getNewPosition(Position position) {
        return position;
    }
}
