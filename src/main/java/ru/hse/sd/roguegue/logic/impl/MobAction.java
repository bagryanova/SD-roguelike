package ru.hse.sd.roguegue.logic.impl;

import ru.hse.sd.roguegue.logic.GameObjectAction;
import ru.hse.sd.roguegue.logic.Move;
import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.status.Status;

import java.util.ArrayList;

public class MobAction implements GameObjectAction {
    long lastUpdate = 0;

    @Override
    public void updateState(Move move) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate < 500) {
            return;
        }
        lastUpdate = currentTime;
        ArrayList<MobState> mobs = Status.gameState.getMobStates();
        for (MobState mob : mobs) {
            mob.updatePosition();
        }
    }
}
