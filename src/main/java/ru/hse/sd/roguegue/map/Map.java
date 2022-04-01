package ru.hse.sd.roguegue.map;

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

    public void printMap() {
        for (int i = 0; i < cellArray.length; ++i) {
            for (int j = 0; j < cellArray[i].length; ++j) {
                System.out.print(cellArray[i][j] == CellType.GROUND ? "G" : ".");
            }
            System.out.println();
        }
    }
}
