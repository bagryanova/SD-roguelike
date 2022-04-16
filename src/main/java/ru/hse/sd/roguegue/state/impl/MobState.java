package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.UI.MobUI;
import ru.hse.sd.roguegue.state.GameObjectState;
import ru.hse.sd.roguegue.state.MobStrategy;
import ru.hse.sd.roguegue.state.Position;

public class MobState extends GameObjectState {

    private MobStrategy strategy;
    public MobUI mobUI;

    public MobState(MobStrategy mobStrategy, Position position) {
        this.strategy = mobStrategy;
        updatePosition(position);
        mobUI = new MobUI(mobStrategy);
        setUI(mobUI);
    }

    public void updateStrategy(MobStrategy strategy) {
        this.strategy = strategy;
        //иногда (а возможно всегда) нужно поменять mobUI, а точнее view
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
}
