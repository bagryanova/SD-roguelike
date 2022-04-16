package ru.hse.sd.roguegue.map.util;

import ru.hse.sd.roguegue.map.CellType;
import ru.hse.sd.roguegue.state.MobStrategy;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.state.impl.AggressiveStrategy;
import ru.hse.sd.roguegue.state.impl.AvoidingStrategy;
import ru.hse.sd.roguegue.state.impl.MobState;
import ru.hse.sd.roguegue.state.impl.PassiveStrategy;
import ru.hse.sd.roguegue.status.InventoryItem;
import ru.hse.sd.roguegue.status.Status;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class CommonMapUtil {

    private final Random rand = new Random();

    /**
     * @param cells array of cells, represents map
     * @return map with positions and borders
     */
    public CellType[][] setBordersAndPositions(CellType[][] cells) {
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

    /**
     * @param mapCells cells array, represents map
     * Places mobs of specific types on free cells
     */
    public void placeMobs(CellType[][] mapCells) {
        placeMobsOfType(mapCells, "A", 3);
        placeMobsOfType(mapCells, "P", 3);
        placeMobsOfType(mapCells, "C", 2);
    }

    private void placeMobsOfType(CellType[][] mapCells, String mobStrategyType, int mobsNum) {
        for (int c = 0; c < mobsNum; c++) {
            int i = 0, j = 0;
            while (mapCells[i][j] != CellType.GROUND) {
                i = rand.nextInt(0, mapCells.length - 1);
                j = rand.nextInt(0, mapCells[0].length - 1);
            }
            assert mapCells[i][j] == CellType.GROUND;
            MobStrategy mobStrategy = new AggressiveStrategy();
            if (mobStrategyType.equals("P")) {
                mobStrategy = new PassiveStrategy();
            } else if (mobStrategyType.equals("C")) {
                mobStrategy = new AvoidingStrategy();
            }
            Status.gameState.getMobStates().add(new MobState(mobStrategy, new Position(j, i)));
        }
    }

    /**
     * @param mapCells cells array, represents map
     * Initializes and laces inventory items on free cells
     */
    public void placeInventory(CellType[][] mapCells) {
        List<String> mapInventoryObjects = List.of("Helmet", "Sword", "Knife", "Cloak", "Gun", "Boots", "Gloves");
        for (String name : mapInventoryObjects) {
            int i = 0, j = 0;
            while (mapCells[i][j] != CellType.GROUND) {
                i = rand.nextInt(0, mapCells.length - 1);
                j = rand.nextInt(0, mapCells[0].length - 1);
            }
            Status.inventoryMapItems.add(new InventoryItem(name, new Position(j, i), rand.nextInt(3, 9), rand.nextInt(3, 12)));
        }
    }
}
