package ru.hse.sd.roguegue.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapGeneratorUtil {
    private CellType[][] cells = new CellType[0][0];
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
        constraints = new Constraints(0.4, 0.7, 4, 8, 4, 8);
        List<GroundSpace> groundSpaces = setGroundSpaces();
        connectGroundSpaces(groundSpaces);
        return new Map(cells);
    }

    private void initCells(int width, int height) {
        cells = new CellType[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
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
        int groundsAcross = cells.length / constraints.maxW;
        int groundsDown = cells[0].length / constraints.maxH;
        int maxGrounds = groundsAcross * groundsDown;
        int totalGrounds = RandUtil.getRandom((int) (maxGrounds * constraints.minRate), (int) (maxGrounds * constraints.maxRate));
        List<GroundSpace> grounds = new ArrayList<>(totalGrounds);
        List<Integer> coords = generateCoordinates(totalGrounds, maxGrounds - 1);
        coords.forEach(center -> {
            int w = RandUtil.getRandom(constraints.minW, constraints.maxW);
            int h = RandUtil.getRandom(constraints.minH, constraints.maxH);
            int x = constraints.maxW * (center % groundsAcross) + RandUtil.getRandom(0, constraints.maxW - w);
            int y = constraints.maxH * (center / groundsAcross) + RandUtil.getRandom(0, constraints.maxH - h);
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

    private enum Direction {X, Y}

    /**
     * Connects ground spaces by moving from one to another by random number of steps in random directions
     *
     * @param groundSpaces list of generated ground spaces
     */
    private void connectGroundSpaces(List<GroundSpace> groundSpaces) {
        if (groundSpaces.isEmpty()) return;
        GroundSpace cur = groundSpaces.remove(0);
        for (GroundSpace nxt : groundSpaces) {
            int curX = cur.x + cur.w / 2;
            int curY = cur.y + cur.h / 2;
            int stepX = nxt.x + nxt.w / 2 - curX;
            int stepY = nxt.y + nxt.h / 2 - curY;
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
    private static class GroundSpace {

        public final int x;
        public final int y;
        public final int w;
        public final int h;

        public GroundSpace(int x, int y, int w, int h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

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
    private static class Constraints {
        public double minRate;
        public double maxRate;
        public int minW;
        public int maxW;
        public int minH;
        public int maxH;

        public Constraints(double minRate, double maxRate, int minW, int maxW, int minH, int maxH) {
            this.minRate = minRate;
            this.maxRate = maxRate;
            this.minW = minW;
            this.maxW = maxW;
            this.minH = minH;
            this.maxH = maxH;
        }
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
