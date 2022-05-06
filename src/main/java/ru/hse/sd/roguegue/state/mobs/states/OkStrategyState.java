package ru.hse.sd.roguegue.state.mobs.states;

import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.state.mobs.StrategyState;
import ru.hse.sd.roguegue.state.mobs.strategies.AggressiveStrategy;

public class OkStrategyState extends StrategyState {
    public OkStrategyState(MobState state) {
        this.state = state;
        strategy = new AggressiveStrategy();
    }

    /**
     * If mob's number of lives is small - mob will switch to avoiding strategy
     * If mob is far enough from user - mob will patrol his small area
     * If mob is really far from user - mob will stay frozen until user comes closer
     */
    public void updateStrategy() {
        if (state.lives < 3) {
            state.updateStrategyState(new PanicStrategyState(state));
        } else if (!checkDistance(30)) {
            state.updateStrategyState(new PassiveStrategyState(state));
        } else if (!checkDistance(20)) {
            state.updateStrategyState(new PatrolAreaStrategyState(state));
        }
    }
}
