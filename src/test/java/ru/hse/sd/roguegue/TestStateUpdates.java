package ru.hse.sd.roguegue;

import org.junit.jupiter.api.Test;
import ru.hse.sd.roguegue.logic.GameAction;
import ru.hse.sd.roguegue.map.CellType;
import ru.hse.sd.roguegue.map.Map;
import ru.hse.sd.roguegue.status.Constants;
import ru.hse.sd.roguegue.status.Status;

public class TestStateUpdates {
    @Test
    public void testMapStateUpdated() {
        CellType[][] cellTypes = new CellType[Constants.MAP_HEIGHT + 2][Constants.MAP_WIDTH + 2];
        for (int i = 0; i < cellTypes.length; i++) {
            for (int j = 0; j < cellTypes[i].length; j++) {
                if (j % 3 == 0) cellTypes[i][j] = CellType.GROUND;
                else cellTypes[i][j] = CellType.OBSTACLE;
            }
        }
        Map map = new Map(cellTypes);
        GameAction gameAction = new GameAction();
        gameAction.updateState(map);
        assert Status.mapState.getMap() == map;
    }
}
