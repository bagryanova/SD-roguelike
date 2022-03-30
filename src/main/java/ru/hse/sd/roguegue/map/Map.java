package ru.hse.sd.roguegue.map;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Map {
    public final List<CellType> cellArray;

    public Map(@NotNull List<CellType> cellArray) {
        this.cellArray = cellArray;
    }
}
