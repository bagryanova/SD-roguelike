package ru.hse.sd.roguegue.map;

public interface MapBuilder {

    /**
     * Generates rooms and tunnels, connecting those rooms
     */
    void generateMap();

    /**
     * Sets a border around the map
     */
    void setBordersAndPositions();

    /**
     * Sets user position on the map
     */
    void setUserPosition();

    /**
     * Places mobs on the map
     */
    void placeMobs();

    /**
     * Places inventory on the map
     */
    void placeInventory();

    /**
     * Converts array of cells to a Map
     *
     * @return map that was created
     */
    Map mapFromCells();
}
