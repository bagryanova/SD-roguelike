package ru.hse.sd.roguegue.state.mobs.strategies;

import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.mobs.MobStrategy;

/**
 * Abstract class for adding some additional (probably temporary) functionality to the strategy
 */
public abstract class StrategyDecorator extends MobStrategy {
    private MobStrategy decoratedStrategy;

    public StrategyDecorator(MobStrategy strategy) {
        super();
        decoratedStrategy = strategy;
    }

    /**
     * This method will be decorated
     */
    public Position getNewPosition(Position position) {
        return decoratedStrategy.getNewPosition(position);
    }

    public MobStrategy tryRemoveDecorator() {
        return decoratedStrategy;
    }
}
