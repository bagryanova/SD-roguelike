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
        /*System.out.println("CHANGE MAP FROM");
        if (map != null) {
            map.printMap();
        } else {
            System.out.println("null");
        }*/
        map = newMap;
        /*System.out.println("TO");
        if (map != null) {
            map.printMap();
        } else {
            System.out.println("null");
        }*/
        Status.mapUI.displayMap();
        Status.userUI.displayPosition();

    }

    public Map getMap() {
        return map;
    }
}
