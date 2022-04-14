package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.state.GameObjectState;
import ru.hse.sd.roguegue.state.MobStrategy;
import ru.hse.sd.roguegue.state.Position;

public class MobState extends GameObjectState {
    private int strength;
    private int lives;
    private MobStrategy strategy;

    public void updateStrength(int newStrength) {
        strength = newStrength;
    }

    public int getStrength() {
        return strength;
    }

    public void updateLives(int newLives) {
        lives = newLives;
    }

    public int getLives() {
        return lives;
    }

    public void updatePosition() {
        Position newPosition = strategy.getNewPosition(super.getPosition());
        super.updatePosition(newPosition);
    }

    public void updatePosition(Position position) {
        super.updatePosition(position);
    }
}
