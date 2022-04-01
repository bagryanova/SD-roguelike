package ru.hse.sd.roguegue.map;

import ru.hse.sd.roguegue.status.MapMode;
import ru.hse.sd.roguegue.status.Status;

public class MapGenerator {
    private final MapGeneratorUtil mapGeneratorUtil = new MapGeneratorUtil();

    /**
     * According to the map mode either generates or loads map from file
     * @return generated instance of Map
     */
    public Map getMap() {
        if (Status.mapMode == MapMode.RANDOM) {
            return generateMap();
        } else {
            return getMapFromFile();
        }
    }

    private Map generateMap() {
        return mapGeneratorUtil.generateMap(40, 50);
    }

    private Map getMapFromFile() {
        // todo как и хотела
        return null;
    }
}
