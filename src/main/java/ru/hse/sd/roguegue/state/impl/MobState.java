package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.state.GameObjectState;
import ru.hse.sd.roguegue.state.MobStrategy;
import ru.hse.sd.roguegue.state.Position;

public class MobState extends GameObjectState {
    int strength;
    int lives;
    MobStrategy strategy;

    public void updateStrength(int newStrength) {
        strength = newStrength;
    }

    public void updateLives(int newLives) {
        lives = newLives;
    }

    public void updatePosition() {
        Position newPosition = strategy.getNewPosition(super.getPosition());
        super.updatePosition(newPosition);
    }

    public void updatePosition(Position position) {
        super.updatePosition(position);
    }
}
