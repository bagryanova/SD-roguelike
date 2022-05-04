package ru.hse.sd.roguegue.state.mobs;

import ru.hse.sd.roguegue.map.CellType;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.state.mobs.MobStrategy;
import ru.hse.sd.roguegue.state.mobs.strategies.ConfuseStrategyDecorator;
import ru.hse.sd.roguegue.state.mobs.strategies.ReplicatingStrategy;
import ru.hse.sd.roguegue.status.Status;

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

    void setReplicatingStrategy();

    boolean isReplicating();
}
