package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.state.GameObjectState;
import ru.hse.sd.roguegue.state.MobStrategy;
import ru.hse.sd.roguegue.state.Position;

public class MobState extends GameObjectState {
    private MobStrategy strategy = new PassiveStrategy();

    public void updateStrategy(MobStrategy strategy) {
        this.strategy = strategy;
    }

    public void updateStrength(int newStrength) {
        strategy.strength = newStrength;
    }

    public int getStrength() {
        return strategy.strength;
    }

    public void updateLives(int newLives) {
        strategy.lives = newLives;
    }

    public int getLives() {
        return strategy.lives;
    }

    public void updatePosition() {
        Position newPosition = strategy.getNewPosition(super.getPosition());
        super.updatePosition(newPosition);
    }

    public void updatePosition(Position position) {
        super.updatePosition(position);
    }
}
