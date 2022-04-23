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
import java.util.Random;

public class MapBuilderCommons {

    private final Random rand = new Random();

    /**
     * Sets border around the map
     *
     * @param cells array of cells, represents map
     * @return map with border
     */
    public CellType[][] setBordersAndPositions(CellType[][] cells) {
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
        return borderedCells;
    }

    /**
     * Sets user position. User can occupy any sell with type [CellType.GROUND]
     *
     * @param cells array of cells, represents map
     */
    public void setUserPosition(CellType[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j] == CellType.GROUND) {
                    Status.userState.updatePosition(new Position(j, i));
                    return;
                }
            }
        }
    }

    /**
     * @param cells cells array, represents map
     *              Places mobs of specific types on free cells
     */
    public void placeMobs(CellType[][] cells) {
        placeMobsOfType(cells, "A", 3);
        placeMobsOfType(cells, "P", 3);
        placeMobsOfType(cells, "C", 2);
    }

    private void placeMobsOfType(CellType[][] cells, String mobStrategyType, int mobsNum) {
        for (int c = 0; c < mobsNum; c++) {
            int i = 0, j = 0;
            while (cells[i][j] != CellType.GROUND) {
                i = rand.nextInt(0, cells.length - 1);
                j = rand.nextInt(0, cells[0].length - 1);
            }
            assert cells[i][j] == CellType.GROUND;
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
     * @param cells cells array, represents map
     *              Initializes and laces inventory items on free cells
     */
    public void placeInventory(CellType[][] cells) {
        List<String> mapInventoryObjects = List.of("Helmet", "Sword", "Knife", "Cloak", "Gun", "Boots", "Gloves");
        for (String name : mapInventoryObjects) {
            int i = 0, j = 0;
            while (cells[i][j] != CellType.GROUND) {
                i = rand.nextInt(0, cells.length - 1);
                j = rand.nextInt(0, cells[0].length - 1);
            }
            Status.inventoryMapItems.add(new InventoryItem(name, new Position(j, i), rand.nextInt(3, 9), rand.nextInt(3, 12)));
        }
    }
}
