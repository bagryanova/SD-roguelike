package ru.hse.sd.roguegue.state.mobs.states;

import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.StrategyDecorator;
import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.state.mobs.MobStrategy;
import ru.hse.sd.roguegue.state.mobs.StrategyState;
import ru.hse.sd.roguegue.state.mobs.strategies.AreaHoldingStrategy;
import ru.hse.sd.roguegue.state.mobs.strategies.ConfuseStrategyDecorator;
import ru.hse.sd.roguegue.state.mobs.strategies.ReplicatingStrategy;
import ru.hse.sd.roguegue.status.Status;

public class PatrolAreaStrategyState implements StrategyState {

    public PatrolAreaStrategyState(MobState state) {
        this.state = state;
    }

    private MobStrategy strategy = new AreaHoldingStrategy();
    private final MobState state;

    @Override
    public boolean validatePosition(Position position) {
        return strategy.validatePosition(position);
    }

    @Override
    public Position getNewPosition(Position position) {
        return strategy.getNewPosition(position);
    }

    @Override
    public void updateStrategy() {
        if (checkDistance()) {
            state.updateStrategyState(new OkStrategyState(state));
        }
    }

    @Override
    public void setConfuse() {
        strategy = new ConfuseStrategyDecorator(strategy);
    }

    @Override
    public void tryRemoveStrategyDecorator() {
        if (strategy instanceof StrategyDecorator) {
            this.strategy = ((StrategyDecorator) strategy).tryRemoveDecorator();
        }
    }

    @Override
    public void setReplicatingStrategy() {
        strategy = new ReplicatingStrategy();
    }

    @Override
    public boolean isReplicating() {
        return strategy.getClass() == ReplicatingStrategy.class;
    }

    private boolean checkDistance() {
        double xLen = Math.pow(Status.userState.getPosition().getX() - state.getPosition().getX(), 2);
        double yLen = Math.pow(Status.userState.getPosition().getY() - state.getPosition().getY(), 2);
        System.out.println(Math.sqrt(xLen + yLen));
        return Math.sqrt(xLen + yLen) < 20;
    }
}
