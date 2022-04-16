package ru.hse.sd.roguegue.state;

public abstract class StrategyDecorator extends MobStrategy {
    private MobStrategy decoratedStrategy;

    public StrategyDecorator(MobStrategy strategy) {
        super(strategy);
        decoratedStrategy = strategy;
    }

    public Position getNewPosition(Position position) {
        return decoratedStrategy.getNewPosition(position);
    }
}
