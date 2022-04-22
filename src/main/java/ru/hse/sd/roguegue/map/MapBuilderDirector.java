package ru.hse.sd.roguegue.map;

import ru.hse.sd.roguegue.map.util.MapFromFileBuilder;
import ru.hse.sd.roguegue.map.util.MapRandomBuilder;
import ru.hse.sd.roguegue.status.MapMode;

public class MapBuilderDirector {

    MapBuilder builder;

    public MapBuilderDirector(MapMode mode, int width, int height, Fabric fabric) {
        if (mode == MapMode.FILE) {
            builder = new MapFromFileBuilder(fabric);
        } else if (mode == MapMode.RANDOM) {
            builder = new MapRandomBuilder(width, height, fabric);
        }
    }

    public Map build() {
        builder.generateMap();
        CellType[][] cells = builder.setBordersAndPositions();
        builder.placeInventory(cells);
        builder.placeMobs(cells);
        return builder.mapFromCells(cells);
    }
}
