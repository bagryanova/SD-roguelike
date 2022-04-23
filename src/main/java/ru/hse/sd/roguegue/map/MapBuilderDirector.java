package ru.hse.sd.roguegue.map;

import ru.hse.sd.roguegue.map.util.MapFromFileBuilder;
import ru.hse.sd.roguegue.map.util.MapRandomBuilder;
import ru.hse.sd.roguegue.status.MapMode;

public class MapBuilderDirector {

    MapBuilder builder;

    /**
     * Creates new MapBuilderDirector
     *
     * @param mode   defines if map should be loaded from file or generated randomly
     * @param width  map width
     * @param height map height
     * @param fabric fabric for mobs creation
     */
    public MapBuilderDirector(MapMode mode, int width, int height, Fabric fabric) {
        if (mode == MapMode.FILE) {
            builder = new MapFromFileBuilder(fabric);
        } else if (mode == MapMode.RANDOM) {
            builder = new MapRandomBuilder(width, height, fabric);
        }
    }

    /**
     * Builds map step by step
     *
     * @return created map
     */
    public Map build() {
        builder.generateMap();
        builder.setBordersAndPositions();
        builder.setUserPosition();
        builder.placeInventory();
        builder.placeMobs();
        return builder.mapFromCells();
    }
}
