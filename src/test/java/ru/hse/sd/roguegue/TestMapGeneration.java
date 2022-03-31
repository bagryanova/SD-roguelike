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
        for (int cnt = 0; cnt < 50; cnt++) {
            MapGenerator mapGenerator = new MapGenerator();
            Map map = mapGenerator.getMap();
            int fin = 0;
            for (int i = 0; i < map.getCellArray().length; i++) {
                boolean space = false;
                for (int j = 0; j < map.getCellArray()[0].length; j++) {
                    if (map.getCell(i, j) == CellType.GROUND) {
                        space = true;
                        fin = 1;
                    }
                }
                if (fin == 1 && !space) fin = 2;
                if (fin == 2 || fin  == 0) assert !space;
                else assert space;
            }
        }
    }
}
