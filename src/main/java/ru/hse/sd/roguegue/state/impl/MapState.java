package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.map.Map;
import ru.hse.sd.roguegue.status.Status;

/**
 * Class for the information about map
 */
public class MapState {
    private Map map;

    /**
     * @param newMap
     * update map according to the newMap and display changes on the screen (map and everything which should be on it)
     */
    public void updateMap(Map newMap) {
        map = newMap;
        Status.mapUI.displayMap();
        Status.userState.userUI.displayPosition();

    }

    public Map getMap() {
        return map;
    }
}
