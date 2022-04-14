package ru.hse.sd.roguegue.map.util;

import ru.hse.sd.roguegue.map.CellType;
import ru.hse.sd.roguegue.map.Map;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class MapFromFileUtil {
    private final Random rand = new Random();
    private final String pathName =
            "src" + File.separatorChar + "main" + File.separatorChar + "resources" + File.separatorChar + "templates";

    /**
     * Gets map representation from random file and converts it to Map type
     *
     * @return converted Map
     */
    public Map getRandomMapFromFile() {
        File file = new File(pathName);
        String[] fileList = file.list();
        if (fileList == null || fileList.length == 0) {
            return null;
        }
        File mapTemplate = new File(pathName + File.separatorChar + fileList[rand.nextInt(fileList.length - 1)]);
        CellType[][] cellArray = convertToMapFromFile(mapTemplate);
        CommonMapUtil commonMapUtil = new CommonMapUtil();
        return new Map(commonMapUtil.setBoundsAndPositions(cellArray));
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
