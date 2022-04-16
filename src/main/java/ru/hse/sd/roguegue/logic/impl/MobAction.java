package ru.hse.sd.roguegue.logic.impl;

import ru.hse.sd.roguegue.logic.GameObjectAction;
import ru.hse.sd.roguegue.logic.Move;
import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.status.Status;

import java.util.ArrayList;

public class MobAction implements GameObjectAction {
    @Override
    public void updateState(Move move) {
        ArrayList<MobState> mobs = Status.gameState.getMobStates();
        for (MobState mob : mobs) {
            mob.updatePosition();
        }
        ArrayList<MobState> copyMobs = new ArrayList<>(Status.gameState.getMobStates());
        for (MobState mob : copyMobs) {
            if (!mob.alive) {
                Status.gameState.getMobStates().remove(mob);
            }
        }
    }
}
