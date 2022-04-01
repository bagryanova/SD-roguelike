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
        for (CellType[] cellTypes : cellArray) {
            for (int j = 0; j < cellTypes.length; ++j) {
                if (cellTypes[j] == CellType.EXIT) System.out.print("E");
//                else if (cellTypes[j] == CellType.PLAYER) System.out.print("P");
                else if (cellTypes[j] == CellType.GROUND) System.out.print("G");
                else if (cellTypes[j] == CellType.OBSTACLE) System.out.print(".");
//                System.out.print(cellArray[i][j]);
            }
            System.out.println();
        }
    }
}
