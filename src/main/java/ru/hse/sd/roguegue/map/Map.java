package ru.hse.sd.roguegue.map;

/**
 * Represents the map, map is stored in the array of cells
 * @param cellArray array of cells the map consists of, each cell represents a cell type
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

    /**
     * Sets cell to specified position
     * @param x x coordinate
     * @param y y coordinate
     * @param cell to be set
     */
    public void setCell(int x, int y, CellType cell) {
        cellArray[x][y] = cell;
    }
}
