package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.UI.MobUI;
import ru.hse.sd.roguegue.state.GameObjectState;
import ru.hse.sd.roguegue.state.MobStrategy;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.Status;

public class MobState extends GameObjectState {

    private MobStrategy strategy;
    public MobUI mobUI;
    public boolean alive = true;

    public MobState(MobStrategy mobStrategy, Position position) {
        this.strategy = mobStrategy;
        updatePosition(position);
        mobUI = new MobUI(mobStrategy);
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
        if (this.getPosition().equals(Status.userState.getPosition())) {
            fight();
        }
    }

    public void fight() {
        if (getStrength() > Status.userState.getStrength()) {
            Status.userState.loseHealth(getStrength() / 2);
            if (Status.userState.getLives() <= 0) {
                Status.gameStatus = GameStatus.LOSE;
                Status.userState.setInitialValues();
                Status.gameState.changeScreen();
            }
        } else {
//            Status.userState.updateExp(Status.userState.getExp() + 20);
            updateLives(getLives() - 1);
            if (getLives() <= 0) {
                alive = false;
                Status.userState.defeatMob();
            }
        }
    }
}
