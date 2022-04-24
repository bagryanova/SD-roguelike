package ru.hse.sd.roguegue.logic.impl;

import ru.hse.sd.roguegue.logic.GameObjectAction;
import ru.hse.sd.roguegue.logic.Move;
import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.status.Status;

import java.util.ArrayList;

public class MobAction implements GameObjectAction {
    /**
     * Update mob's state, remove dead mobs
     */
    @Override
    public void updateState(Move move) {
        ArrayList<MobState> copyMobs = new ArrayList<>(Status.gameState.getMobStates());
        for (int i = 0; i < copyMobs.size(); i++) {
           Status.gameState.getMobStates().get(i).updatePosition();
        }
        copyMobs = new ArrayList<>(Status.gameState.getMobStates());
        for (MobState mob : copyMobs) {
            if (!mob.alive) {
                Status.gameState.getMobStates().remove(mob);
            }
        }
    }
}
