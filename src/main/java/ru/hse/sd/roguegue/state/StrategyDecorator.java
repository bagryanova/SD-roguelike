package ru.hse.sd.roguegue.state;

/**
 * Abstract class for adding some additional (probably temporary) functionality to the strategy
 */
public abstract class StrategyDecorator extends MobStrategy {
    private MobStrategy decoratedStrategy;

    public StrategyDecorator(MobStrategy strategy) {
        super(strategy);
        decoratedStrategy = strategy;
    }

    /**
     * This method will be decorated
     */
    public Position getNewPosition(Position position) {
        return decoratedStrategy.getNewPosition(position);
    }
}
