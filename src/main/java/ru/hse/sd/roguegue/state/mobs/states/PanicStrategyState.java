package ru.hse.sd.roguegue.state.mobs.states;

import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.state.mobs.MobStrategy;
import ru.hse.sd.roguegue.state.mobs.StrategyState;
import ru.hse.sd.roguegue.state.mobs.strategies.AvoidingStrategy;

public class PanicStrategyState extends StrategyState {
    public PanicStrategyState(MobState state) {
        this.state = state;
    }

    private MobStrategy strategy = new AvoidingStrategy();
    private final MobState state;

    public void updateStrategy() {
        if (state.lives >= 3) {
            state.updateStrategyState(new OkStrategyState(state));
        }
    }

    /**
     * Get new mob's position
     */
    public Position getNewPosition(Position position) {
        System.out.println("It's panic");
        return strategy.getNewPosition(position);
    }
}
