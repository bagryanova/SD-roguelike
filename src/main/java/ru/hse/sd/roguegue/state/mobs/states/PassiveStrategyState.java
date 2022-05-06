package ru.hse.sd.roguegue.state.mobs.states;

import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.state.mobs.StrategyState;
import ru.hse.sd.roguegue.state.mobs.strategies.PassiveStrategy;

/**
 * Whenever distance from mob to user is big enough - mob's strategy will switch
 * to passive strategy and remain passive until user comes closer
 */
public class PassiveStrategyState extends StrategyState {

    public PassiveStrategyState(MobState state) {
        this.state = state;
        strategy = new PassiveStrategy();
    }

    /**
     * If mob's distance from user is less than 30, mob will switch back to its initial strategy
     */
    @Override
    public void updateStrategy() {
        if (checkDistance(30)) {
            state.updateStrategyState(new OkStrategyState(state));
        }
    }
}
