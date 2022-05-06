package ru.hse.sd.roguegue.state.mobs;

import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.StrategyDecorator;
import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.state.mobs.strategies.ConfuseStrategyDecorator;
import ru.hse.sd.roguegue.status.Status;

/**
 * Abstract class which represents mob's current state
 */
public abstract class StrategyState {

    protected MobStrategy strategy;
    protected MobState state;

    /**
     * Validate new mob's position
     */
    public boolean validatePosition(Position position) {
        return strategy.validatePosition(position);
    }

    /**
     * Get new mob's position
     */
    public Position getNewPosition(Position position) {
        return strategy.getNewPosition(position);
    }

    /**
     * Update mob's strategy according to the set of rules
     */
    public abstract void updateStrategy();

    /**
     * Set confuse decorator
     */
    public void setConfuse() {
        strategy = new ConfuseStrategyDecorator(strategy);
    }

    /**
     * Remove confused state
     */
    public void tryRemoveStrategyDecorator() {
        if (strategy instanceof StrategyDecorator) {
            this.strategy = ((StrategyDecorator) strategy).tryRemoveDecorator();
        }
    }

    /**
     * Check if the distance between user and mob is smaller than distanceToCheck
     * @param distanceToCheck distsnce will be compared with it
     * @return true if distance is smaller than the distanceToCheck
     */
    protected boolean checkDistance(int distanceToCheck) {
        double xLen = Math.pow(Status.userState.getPosition().getX() - state.getPosition().getX(), 2);
        double yLen = Math.pow(Status.userState.getPosition().getY() - state.getPosition().getY(), 2);
        System.out.println(Math.sqrt(xLen + yLen));
        return Math.sqrt(xLen + yLen) < distanceToCheck;
    }
}
