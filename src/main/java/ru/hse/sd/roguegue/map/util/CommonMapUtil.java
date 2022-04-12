package ru.hse.sd.roguegue.map.util;

import ru.hse.sd.roguegue.map.CellType;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.status.Status;

public class CommonMapUtil {
    public CellType[][] setBoundsAndPositions(CellType[][] cells) {
        boolean setPosition = true;
        CellType[][] borderedCells = new CellType[cells.length + 2][cells[0].length + 2];
        for (int i = 0; i < borderedCells.length; i++) {
            for (int j = 0; j < borderedCells[0].length; j++) {
                if (i == 0 || i == borderedCells.length - 1 || j == 0 || j == borderedCells[0].length - 1) {
                    borderedCells[i][j] = CellType.OBSTACLE;
                } else {
                    borderedCells[i][j] = cells[i - 1][j - 1];
                }
            }
        }
        for (int i = 0; i < borderedCells.length; i++) {
            for (int j = 0; j < borderedCells[0].length; j++) {
                if (borderedCells[i][j] == CellType.GROUND && setPosition) {
                    setPosition = false;
                    Status.userState.updatePosition(new Position(j, i));
                }
            }
        }
        return borderedCells;
    }
}
