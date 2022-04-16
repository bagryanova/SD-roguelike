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
        if (this.getPosition().equals(Status.userState.getPosition())) {
            fight(this);
        }
    }

    private void fight(MobState mob) {
        System.out.println("fight 2");
        // todo finish game if user died
        if (mob.getStrength() > Status.userState.getStrength()) {
            Status.userState.updateHealth(mob.getStrength() / 2);
            if (Status.userState.getLives() <= 0) {
                Status.gameStatus = GameStatus.LOSE;
                // выставила юзеру изначальные значения
                Status.userState.setInitialValues();
                Status.gameState.changeScreen();
            }
        } else {
            System.out.println("fight 1");
//            Status.userState.updateExp(Status.userState.getExp() + 20);
            mob.updateLives(mob.getLives() - 1);
            if (mob.getLives() <= 0) {
                mob.alive = false;
            }
            Status.userState.defeatMob();
        }
    }
}
