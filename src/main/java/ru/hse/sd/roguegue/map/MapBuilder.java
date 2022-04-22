package ru.hse.sd.roguegue.map;

public interface MapBuilder {
    void generateMap();

    CellType[][] setBordersAndPositions();

    void placeMobs(CellType[][] cells);

    void placeInventory(CellType[][] cells);

    Map mapFromCells(CellType[][] cells);
}
