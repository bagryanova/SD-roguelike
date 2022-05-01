package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.UI.MobUI;
import ru.hse.sd.roguegue.state.GameObjectState;
import ru.hse.sd.roguegue.state.mobs.MobStrategy;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.StrategyDecorator;
import ru.hse.sd.roguegue.state.mobs.strategies.ConfuseStrategyDecorator;
import ru.hse.sd.roguegue.state.mobs.strategies.ReplicatingStrategy;
import ru.hse.sd.roguegue.status.GameStatus;
import ru.hse.sd.roguegue.status.MobType;
import ru.hse.sd.roguegue.status.Status;

public class MobState extends GameObjectState implements Cloneable {

    private MobStrategy strategy;
    public int strength = 0;
    public int lives = 0;
    public MobType mobType;
    public MobUI mobUI;
    public boolean alive = true;

    public MobState(MobStrategy mobStrategy, Position position, MobType mobType) {
        this.strategy = mobStrategy;
        updatePosition(position);
        this.mobUI = new MobUI(mobType);
        this.mobType = mobType;
    }

    /**
     * Add confusing decorator to current mob's strategy
     */
    public void setConfuseStrategy() {
        strategy = new ConfuseStrategyDecorator(strategy);
    }

    /**
     * Get current strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Get current strength
     */
    public int updateStrength(int newStrength) {
        return strength = newStrength;
    }

    /**
     * Update current strength
     */
    public void updateLives(int newLives) {
        lives = newLives;
    }

    /**
     * Get current lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Update position according to strategy
     * For some mobs, which have to do something after changing the position, do it
     * More precisely, create mob's clone if it's time for replicating mobs.
     * And try to remove strategy decorator
     */
    public void updatePosition() {
        Position newPosition = strategy.getNewPosition(super.getPosition());
        super.updatePosition(newPosition);
        if (this.getPosition().equals(Status.userState.getPosition())) {
            fight();
        }
        if (strategy.getClass() == ReplicatingStrategy.class) {
            ReplicatingStrategy replicatingStrategy = (ReplicatingStrategy) strategy;
            if (replicatingStrategy.replicationTime()) {
                MobState clone = clone();
                if (clone != null) {
                    Status.gameState.getMobStates().add(clone);
                    System.out.println("MOB SIZE " + Status.gameState.getMobStates().size());
                }
            }
        }
        tryRemoveStrategyDecorator();
    }

    private void tryRemoveStrategyDecorator() {
        if (strategy instanceof StrategyDecorator) {
            this.strategy = ((StrategyDecorator) strategy).tryRemoveDecorator();
        }
    }

    @Override
    public MobState clone() {
        try {
            MobState clone = (MobState) super.clone();
            clone.strategy = new ReplicatingStrategy();
            clone.mobUI = new MobUI(clone.mobType);
            clone.position = new Position(position.getX(), position.getY());
            clone.updatePosition(clone.strategy.getNewPosition(clone.position));
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
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
