package ru.hse.sd.roguegue;

import org.junit.jupiter.api.Test;
import ru.hse.sd.roguegue.map.CellType;
import ru.hse.sd.roguegue.map.Map;
import ru.hse.sd.roguegue.map.MapGenerator;
import ru.hse.sd.roguegue.status.MapMode;
import ru.hse.sd.roguegue.status.Status;

public class TestMapGeneration {
    @Test
    public void stressTestRandomMapGeneration() {
        Status.mapMode = MapMode.RANDOM;
        for (int cnt = 0; cnt < 100; cnt++) {
            MapGenerator mapGenerator = new MapGenerator();
            Map map = mapGenerator.getMap();
            int fin = 0;
            for (int i = 0; i < map.cellArray().length; i++) {
                boolean space = false;
                for (int j = 0; j < map.cellArray()[0].length; j++) {
                    if (map.getCell(i, j) == CellType.GROUND || map.getCell(i, j) == CellType.EXIT) {
                        assert fin != 2;
                        space = true;
                        fin = 1;
                    }
                }
                if (fin == 1 && !space) fin = 2;
                if (fin == 2 || fin == 0) assert !space;
                else assert space;
            }
        }
    }

    @Test
    public void stressTestMapFromFileGeneration() {
        Status.mapMode = MapMode.FILE;
        for (int cnt = 0; cnt < 100; cnt++) {
            MapGenerator mapGenerator = new MapGenerator();
            Map map = mapGenerator.getMap();
            int fin = 0;
            for (int i = 0; i < map.cellArray().length; i++) {
                boolean space = false;
                for (int j = 0; j < map.cellArray()[0].length; j++) {
                    if (map.getCell(i, j) == CellType.GROUND || map.getCell(i, j) == CellType.EXIT) {
                        assert fin != 2;
                        space = true;
                        fin = 1;
                    }
                }
                if (fin == 1 && !space) fin = 2;
                if (fin == 2 || fin == 0) assert !space;
                else assert space;
            }
        }
    }
}
