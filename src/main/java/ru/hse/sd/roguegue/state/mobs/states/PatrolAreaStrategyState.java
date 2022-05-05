package ru.hse.sd.roguegue.state.mobs.states;

import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.state.mobs.MobStrategy;
import ru.hse.sd.roguegue.state.mobs.StrategyState;
import ru.hse.sd.roguegue.state.mobs.strategies.AreaHoldingStrategy;
import ru.hse.sd.roguegue.status.Status;

public class PatrolAreaStrategyState extends StrategyState {

    public PatrolAreaStrategyState(MobState state) {
        this.state = state;
    }

    private MobStrategy strategy = new AreaHoldingStrategy();
    private final MobState state;

    @Override
    public Position getNewPosition(Position position) {
        return strategy.getNewPosition(position);
    }

    /**
     * If mob is close to user, mob will switch back to its initial strategy
     */
    @Override
    public void updateStrategy() {
        if (checkDistance()) {
            state.updateStrategyState(new OkStrategyState(state));
        }
    }

    private boolean checkDistance() {
        double xLen = Math.pow(Status.userState.getPosition().getX() - state.getPosition().getX(), 2);
        double yLen = Math.pow(Status.userState.getPosition().getY() - state.getPosition().getY(), 2);
        System.out.println(Math.sqrt(xLen + yLen));
        return Math.sqrt(xLen + yLen) < 20;
    }
}
