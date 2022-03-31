package ru.hse.sd.roguegue.map;

import java.util.List;

public record Map(CellType[][] cellArray) {

    public CellType getCell(int x, int y) {
        return cellArray[x][y];
    }

    public void setCell(int x, int y, CellType cell) {
        cellArray[x][y] = cell;
    }
}
