package ru.hse.sd.roguegue.map;

import ru.hse.sd.roguegue.status.Constants;
import ru.hse.sd.roguegue.map.util.MapFromFileBuilder;
import ru.hse.sd.roguegue.map.util.MapRandomBuilder;
import ru.hse.sd.roguegue.status.MapMode;
import ru.hse.sd.roguegue.status.Status;

public class MapGenerator {
    private final MapRandomBuilder mapGeneratorUtil = new MapRandomBuilder(Constants.MAP_WIDTH, Constants.MAP_HEIGHT, null);
    private final MapFromFileBuilder mapFromFileUtil = new MapFromFileBuilder(null);

    /**
     * According to the map mode either generates or loads map from file
     *
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
        return mapGeneratorUtil.generateMap(Constants.MAP_WIDTH, Constants.MAP_HEIGHT);
    }

    private Map getMapFromFile() {
        return mapFromFileUtil.getRandomMapFromFile();
    }
}
