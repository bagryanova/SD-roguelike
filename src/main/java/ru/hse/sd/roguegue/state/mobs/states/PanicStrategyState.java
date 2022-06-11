package ru.hse.sd.roguegue.state.mobs.states;

import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.state.mobs.StrategyState;
import ru.hse.sd.roguegue.state.mobs.strategies.AvoidingStrategy;

public class PanicStrategyState extends StrategyState {
    public PanicStrategyState(MobState state) {
        this.state = state;
        strategy = new AvoidingStrategy();
    }

    public void updateStrategy() {
        if (state.lives >= 3) {
            state.updateStrategyState(new OkStrategyState(state));
        }
    }
}
