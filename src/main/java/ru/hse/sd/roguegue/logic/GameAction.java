package ru.hse.sd.roguegue.logic;

import ru.hse.sd.roguegue.map.Map;
import ru.hse.sd.roguegue.status.Status;

public class GameAction {
    /**
     * Updates map according to the new map state
     * @param map new map state
     */
    public void updateState(Map map) {
        Status.mapState.updateMap(map);
        Status.gameState.startLevel();
    }
}

