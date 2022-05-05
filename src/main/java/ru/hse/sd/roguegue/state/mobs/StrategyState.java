package ru.hse.sd.roguegue.state.mobs;

import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.StrategyDecorator;
import ru.hse.sd.roguegue.state.mobs.strategies.ConfuseStrategyDecorator;

public abstract class StrategyState {

    private MobStrategy strategy;

    /**
     * Validate new mob's position
     */
    public boolean validatePosition(Position position) {
        return strategy.validatePosition(position);
    }

    /**
     * Get new mob's position
     */
    public abstract Position getNewPosition(Position position);

    public abstract void updateStrategy();

    public void setConfuse() {
        strategy = new ConfuseStrategyDecorator(strategy);
    }

    public void tryRemoveStrategyDecorator() {
        if (strategy instanceof StrategyDecorator) {
            this.strategy = ((StrategyDecorator) strategy).tryRemoveDecorator();
        }
    }
}
