package ru.hse.sd.roguegue.state.mobs;

import ru.hse.sd.roguegue.state.Position;

public interface StrategyState {

    /**
     * Validate new mob's position
     */
    boolean validatePosition(Position position);

    /**
     * Get new mob's position
     */
    Position getNewPosition(Position position);

    void updateStrategy();

    void setConfuse();

    void tryRemoveStrategyDecorator();
}
