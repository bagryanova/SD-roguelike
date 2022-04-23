package ru.hse.sd.roguegue.map.util;

import ru.hse.sd.roguegue.map.CellType;
import ru.hse.sd.roguegue.map.Fabric;
import ru.hse.sd.roguegue.map.Map;
import ru.hse.sd.roguegue.map.MapBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class MapFromFileBuilder implements MapBuilder {
    private CellType[][] currentCells = new CellType[0][0];
    private final Random rand = new Random();
    private final String pathName =
            "src" + File.separatorChar + "main" + File.separatorChar + "resources" + File.separatorChar + "templates";
    private MapBuilderCommons commons = new MapBuilderCommons();
    private final Fabric fabric;

    public MapFromFileBuilder(Fabric fabric) {
        this.fabric = fabric;
    }

    /**
     * Gets map representation from random file and converts it to an array of cells
     */
    @Override
    public void generateMap() {
        File file = new File(pathName);
        String[] fileList = file.list();
        if (fileList == null || fileList.length == 0) {
            return;
        }
        File mapTemplate = new File(pathName + File.separatorChar + fileList[rand.nextInt(fileList.length - 1)]);
        currentCells = convertToMapFromFile(mapTemplate);
    }

    /**
     * See {@link MapBuilder#setBordersAndPositions()}, {@link MapBuilderCommons#setBordersAndPositions(CellType[][])}
     */
    @Override
    public void setBordersAndPositions() {
        currentCells = commons.setBordersAndPositions(currentCells);
    }

    /**
     * See {@link MapBuilder#setUserPosition()}, {@link MapBuilderCommons#setUserPosition(CellType[][])}
     */
    @Override
    public void setUserPosition() {
        commons.setUserPosition(currentCells);
    }

    /**
     * See {@link MapBuilder#placeMobs()}, {@link MapBuilderCommons#placeMobs(CellType[][])}
     */
    @Override
    public void placeMobs() {
        commons.placeMobs(currentCells);
    }

    /**
     * See {@link MapBuilder#placeInventory()}, {@link MapBuilderCommons#placeInventory(CellType[][])}
     */
    @Override
    public void placeInventory() {
        commons.placeInventory(currentCells);
    }

    /**
     * See {@link MapBuilder#mapFromCells()}
     */
    @Override
    public Map mapFromCells() {
        return new Map(currentCells);
    }

    private CellType[][] convertToMapFromFile(File mapTemplate) {
        CellType[][] cellArray = new CellType[42][52];
        AtomicInteger lineNum = new AtomicInteger();
        try (Stream<String> lines = Files.lines(mapTemplate.toPath())) {
            lines.forEachOrdered(line -> {
                var charArr = line.toCharArray();
                for (int i = 0; i < line.length(); i++) {
                    if (charArr[i] == '.') {
                        cellArray[lineNum.get()][i] = CellType.OBSTACLE;
                    } else if (charArr[i] == 'G') {
                        cellArray[lineNum.get()][i] = CellType.GROUND;
                    } else if (charArr[i] == 'E') {
                        cellArray[lineNum.get()][i] = CellType.EXIT;
                    }
                }
                lineNum.getAndIncrement();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cellArray;
    }
}
