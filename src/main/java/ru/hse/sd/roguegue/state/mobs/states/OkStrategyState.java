package ru.hse.sd.roguegue.state.mobs.states;

import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.StrategyDecorator;
import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.state.mobs.MobStrategy;
import ru.hse.sd.roguegue.state.mobs.StrategyState;
import ru.hse.sd.roguegue.state.mobs.strategies.AggressiveStrategy;
import ru.hse.sd.roguegue.state.mobs.strategies.ConfuseStrategyDecorator;

public class OkStrategyState implements StrategyState {
    public  OkStrategyState(MobState state) {
        this.state = state;
    }

    private MobStrategy strategy = new AggressiveStrategy();
    private final MobState state;

    /**
     * Validate new mob's position
     */
    public boolean validatePosition(Position position) {
        return strategy.validatePosition(position);
    }

    public void updateStrategy() {
        if (state.lives < 3) {
            state.updateStrategyState(new PanicStrategyState(state));
        }
    }

    /**
     * Get new mob's position
     */
    public Position getNewPosition(Position position) {
        System.out.println("It's ok");
        return strategy.getNewPosition(position);
    }

    public void setConfuse(){
        strategy = new ConfuseStrategyDecorator(strategy);
    }

    public void tryRemoveStrategyDecorator() {
        if (strategy instanceof StrategyDecorator) {
            this.strategy = ((StrategyDecorator) strategy).tryRemoveDecorator();
        }
    }


}
