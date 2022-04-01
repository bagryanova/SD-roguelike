package ru.hse.sd.roguegue.state.impl;

import ru.hse.sd.roguegue.map.Map;

public class MapState {
    private Map map;

    public void updateMap(Map newMap) {
        System.out.println("CHANGE MAP FROM");
        if (map != null) {
            map.printMap();
        } else {
            System.out.println("null");
        }
        map = newMap;
        System.out.print("TO");
        if (map != null) {
            map.printMap();
        } else {
            System.out.println("null");
        }
    }

    public Map getMap() {
        return map;
    }
}
