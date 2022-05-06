package ru.hse.sd.roguegue.state.mobs.states;

import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.state.mobs.StrategyState;
import ru.hse.sd.roguegue.state.mobs.strategies.AreaHoldingStrategy;

/**
 * Whenever distance from mob to user is big enough - mob's strategy will switch to
 * area holding strategy and remain so until user comes closer
 */
public class PatrolAreaStrategyState extends StrategyState {

    public PatrolAreaStrategyState(MobState state) {
        this.state = state;
        strategy = new AreaHoldingStrategy();
    }

    /**
     * If mob's distance from user is less than 20, mob will switch back to its initial strategy or switch to avoiding strategy
     */
    @Override
    public void updateStrategy() {
        if (checkDistance(20)) {
            state.updateStrategyState(new OkStrategyState(state));
        }
    }
}
