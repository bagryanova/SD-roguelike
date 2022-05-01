package ru.hse.sd.roguegue.state.mobs;

import ru.hse.sd.roguegue.map.CellType;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.status.Status;

public class MobStrategy {
    public MobStrategy() {}

    /**
     * Validate new mob's position
     */
    public boolean validatePosition(Position position) {
        CellType[][] cells = Status.mapState.getMap().cellArray();
        return cells[position.getY()][position.getX()] == CellType.GROUND;
    }

    /**
     * Get new mob's position
     */
    public Position getNewPosition(Position position) {
        return position;
    }

}
