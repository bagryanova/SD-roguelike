package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.map.Map;
import ru.hse.sd.roguegue.state.GameObjectState;

public class MapState {
    private Map map;

    public void updateMap(Map newMap) {
        map = newMap;
    }

    public Map getMap() {
        return map;
    }
}
