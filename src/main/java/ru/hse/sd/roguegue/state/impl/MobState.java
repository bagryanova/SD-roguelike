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

    public void setConfuseStrategy() {
        strategy = new ConfuseStrategyDecorator(strategy);
    }

    /**
     * Get current strength
     */
    public int getStrength() {
        return strategy.strength;
    }

    /**
     * Update current strength
     */
    public void updateLives(int newLives) {
        strategy.lives = newLives;
    }

    /**
     * Get current lives
     */
    public int getLives() {
        return strategy.lives;
    }

    /**
     * Update position according to strategy
     */
    public void updatePosition() {
        Position newPosition = strategy.getNewPosition(super.getPosition());
        super.updatePosition(newPosition);
        if (this.getPosition().equals(Status.userState.getPosition())) {
            fight();
        }
    }

    /**
     * Compare strengths of user and mob and update health and lives according to the characteristics
     */
    public void fight() {
        if (getStrength() > Status.userState.getStrength()) {
            Status.userState.loseHealth(getStrength() / 2 + 1);
            if (Status.userState.getLives() <= 0) {
                Status.gameStatus = GameStatus.LOSE;
                Status.userState.setInitialValues();
                Status.gameState.changeScreen();
            }
        } else {
            System.out.println("lives mob " + getLives());
            updateLives(getLives() - 1);
            if (getLives() <= 0) {
                alive = false;
                Status.userState.defeatMob();
            }
        }
    }
}
