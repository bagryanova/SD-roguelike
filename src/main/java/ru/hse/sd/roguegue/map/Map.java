package ru.hse.sd.roguegue.map;

import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.status.Status;

/**
 * Represents the map, map is stored in the array of cells
 */
public record Map(CellType[][] cellArray) {

    /**
     * @param x x coordinate
     * @param y y coordinate
     * @return specific cell with coordinates x and y
     */
    public CellType getCell(int x, int y) {
        return cellArray[x][y];
    }

    public CellType getCell(Position position) {
        return cellArray[position.getY()][position.getX()];
    }

    /**
     * Sets cell to specified position
     *
     * @param x    x coordinate
     * @param y    y coordinate
     * @param cell to be set
     */
    public void setCell(int x, int y, CellType cell) {
        cellArray[x][y] = cell;
    }
}
