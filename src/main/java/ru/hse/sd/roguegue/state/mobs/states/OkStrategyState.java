package ru.hse.sd.roguegue.state.mobs.states;

import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.state.mobs.MobStrategy;
import ru.hse.sd.roguegue.state.mobs.StrategyState;
import ru.hse.sd.roguegue.state.mobs.strategies.AggressiveStrategy;
import ru.hse.sd.roguegue.status.Status;

public class OkStrategyState extends StrategyState {
    public OkStrategyState(MobState state) {
        this.state = state;
    }

    private MobStrategy strategy = new AggressiveStrategy();
    private final MobState state;

    /**
     * If mob's number of lives is small - mob will switch to avoiding strategy
     * If mob is far enough from user - mob will patrol his small area
     */
    public void updateStrategy() {
        if (state.lives < 3) {
            state.updateStrategyState(new PanicStrategyState(state));
        } else if (!checkDistance()) {
            state.updateStrategyState(new PatrolAreaStrategyState(state));
        }
    }

    /**
     * Get new mob's position
     */
    public Position getNewPosition(Position position) {
        System.out.println("It's ok");
        return strategy.getNewPosition(position);
    }

    private boolean checkDistance() {
        double xLen = Math.pow(Status.userState.getPosition().getX() - state.getPosition().getX(), 2);
        double yLen = Math.pow(Status.userState.getPosition().getY() - state.getPosition().getY(), 2);
        return Math.sqrt(xLen + yLen) < 20;
    }
}
