package ru.hse.sd.roguegue.map.util;

import ru.hse.sd.roguegue.map.CellType;
import ru.hse.sd.roguegue.map.Map;
import ru.hse.sd.roguegue.state.Position;
import ru.hse.sd.roguegue.status.InventoryItem;
import ru.hse.sd.roguegue.status.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMapGeneratorUtil {
    private CellType[][] cells = new CellType[0][0];
    private final Random rand = new Random();
    private Constraints constraints;

    /**
     * Initializes new map, sets constraints for its creation, generates random number of separated ground spaces
     * and connects them in a way that all the ground spaces are connected either by paths or by paths and other ground spaces
     *
     * @param width  map width
     * @param height map height
     * @return randomly generated map
     */
    public Map generateMap(int width, int height) {
        initCells(width, height);
        constraints = new Constraints(0.4, 0.75, 5, 10, 5, 10);
        List<GroundSpace> groundSpaces = setGroundSpaces();
        connectGroundSpaces(groundSpaces);
        GroundSpace gs = groundSpaces.get(rand.nextInt(groundSpaces.size()));
        placeInventory(groundSpaces);
        placeMobs();
        cells[gs.x() + 2][gs.y() + 2] = CellType.EXIT;
        CommonMapUtil commonMapUtil = new CommonMapUtil();
        return new Map(commonMapUtil.setBoundsAndPositions(cells));
    }

    private void placeMobs() {
        int aggressiveMobsCount = rand.nextInt(2, 5);
        int passiveMobsCount = rand.nextInt(2, 6);
        int fearfulMobsCount = rand.nextInt(2, 5);
        placeMobsOfType(aggressiveMobsCount, CellType.AGGRESSIVE_MOB);
        placeMobsOfType(passiveMobsCount, CellType.PASSIVE_MOB);
        placeMobsOfType(fearfulMobsCount, CellType.FEARFUL_MOB);
    }

    private void placeMobsOfType(int mobsCount, CellType mobType) {
        for (int c = 0; c < mobsCount; c++) {
            int i = 0, j = 0;
            while (cells[j][i] != CellType.GROUND) {
                i = rand.nextInt(1, cells[0].length - 1);
                j = rand.nextInt(1, cells.length - 1);
            }
            cells[j][i] = mobType;
        }
    }

    private void placeInventory(List<GroundSpace> groundSpaces) {
        for (String item : Status.inventoryObjects) {
            GroundSpace gs = groundSpaces.get(rand.nextInt(groundSpaces.size()));
            Position position = getRandomGroundCell(gs);
            Status.inventory.add(new InventoryItem(item, position));
            cells[position.getX()][position.getY()] = CellType.INVENTORY;
        }
    }

    private Position getRandomGroundCell(GroundSpace gs) {
        return new Position(rand.nextInt(gs.x + 1, gs.x + gs.w - 1), rand.nextInt(gs.y() + 1, gs.y() + gs.h() - 1));
    }

    private void initCells(int width, int height) {
        cells = new CellType[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = CellType.OBSTACLE;
            }
        }
    }

    /**
     * Generates distinct coordinated for ground spaces, sets random sizes to each one and fills the cells accordingly
     *
     * @return list of all generated ground spaces
     */
    private List<GroundSpace> setGroundSpaces() {
        int groundsAcross = cells.length / constraints.maxW();
        int groundsDown = cells[0].length / constraints.maxH();
        int maxGrounds = groundsAcross * groundsDown;
        int totalGrounds = RandUtil.getRandom((int) (maxGrounds * constraints.minRate()), (int) (maxGrounds * constraints.maxRate()));
        if (totalGrounds <= 0) totalGrounds = 1;
        if (maxGrounds < totalGrounds) maxGrounds = totalGrounds + 1;
        List<GroundSpace> grounds = new ArrayList<>(totalGrounds);
        List<Integer> coords = generateCoordinates(totalGrounds, maxGrounds - 1);
        coords.forEach(center -> {
            int w = RandUtil.getRandom(constraints.minW(), constraints.maxW());
            int h = RandUtil.getRandom(constraints.minH(), constraints.maxH());
            int x = constraints.maxW() * (center % groundsAcross) + RandUtil.getRandom(0, constraints.maxW() - w);
            int y = constraints.maxH() * (center / groundsAcross) + RandUtil.getRandom(0, constraints.maxH() - h);
            GroundSpace ground = new GroundSpace(x, y, w, h);
            ground.setCells(cells);
            grounds.add(ground);
        });
        return grounds;
    }

    private List<Integer> generateCoordinates(int groundsNum, int upper) {
        List<Integer> coords = new ArrayList<>();
        int center;
        for (int i = 0; i < groundsNum; i++) {
            do {
                center = RandUtil.getRandom(0, upper);
            } while (coords.contains(center));
            coords.add(center);
        }
        return coords;
    }

    /**
     * Connects ground spaces by moving from one to another by random number of steps in random directions
     *
     * @param groundSpaces list of generated ground spaces
     */
    private void connectGroundSpaces(List<GroundSpace> groundSpaces) {
        enum Direction {X, Y}
        if (groundSpaces.isEmpty()) return;
        GroundSpace cur = groundSpaces.get(0);
        for (GroundSpace nxt : groundSpaces) {
            int curX = cur.x() + cur.w() / 2;
            int curY = cur.y() + cur.h() / 2;
            int stepX = nxt.x() + nxt.w() / 2 - curX;
            int stepY = nxt.y() + nxt.h() / 2 - curY;
            int stepXDir = stepX == 0 ? 1 : stepX / Math.abs(stepX);
            int stepYDir = stepY == 0 ? 1 : stepY / Math.abs(stepY);
            while (!(stepX == 0 && stepY == 0)) {
                Direction dir = RandUtil.getRandom(0, 1) == 1 ? Direction.X : Direction.Y;
                if (dir == Direction.X) {
                    if (stepX == 0) {
                        dir = Direction.Y;
                    }
                } else {
                    if (stepY == 0) {
                        dir = Direction.X;
                    }
                }
                int len = RandUtil.getRandom(1, (Math.abs(dir == Direction.X ? stepX : stepY)));
                for (int i = 0; i < len; i++) {
                    if (dir == Direction.X) {
                        curX += stepXDir;
                    } else {
                        curY += stepYDir;
                    }
                    cells[curX][curY] = CellType.GROUND;
                }
                if (dir == Direction.X) {
                    stepX -= stepXDir * len;
                } else {
                    stepY -= stepYDir * len;
                }
            }
            cur = nxt;
        }
    }

    /**
     * Representation of a rectangular ground space
     */
    private record GroundSpace(int x, int y, int w, int h) {

        public void setCells(CellType[][] cells) {
            for (int i = x + 1; i < x + w - 1; i++) {
                for (int j = y + 1; j < y + h - 1; j++) {
                    cells[i][j] = CellType.GROUND;
                }
            }
        }
    }

    /**
     * Constraints for map generation
     */
    private record Constraints(double minRate, double maxRate, int minW, int maxW, int minH, int maxH) {
    }

    /**
     * Util class for getting random values bounded by constraints
     */
    private static class RandUtil {

        private RandUtil() {
        }

        private static final Random rand = new Random(System.currentTimeMillis());

        public static int getRandom(int lowerBound, int upperBound) {
            return (int) (rand.nextDouble() * ((upperBound - lowerBound) + 1)) + lowerBound;
        }
    }
}
