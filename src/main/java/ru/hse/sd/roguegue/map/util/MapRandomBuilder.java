package ru.hse.sd.roguegue.map.util;

import ru.hse.sd.roguegue.map.*;
import ru.hse.sd.roguegue.map.impl.MagicMobFactory;
import ru.hse.sd.roguegue.map.impl.NatureMobFactory;
import ru.hse.sd.roguegue.map.impl.TechMobFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapRandomBuilder implements MapBuilder {
    private CellType[][] currentCells = new CellType[0][0];
    private final Random rand = new Random();
    MapBuilderCommons commons = new MapBuilderCommons();
    private Constraints constraints;
    private final int width;
    private final int height;
    private MobFactory factory;

    public MapRandomBuilder(int width, int height, MobFactory factory) {
        this.width = width;
        this.height = height;
        this.factory = factory;
    }

    /**
     * Initializes new map, sets constraints for its creation, generates random number of separated ground spaces
     * and connects them in a way that all the ground spaces are connected either by paths or by paths and other ground spaces
     */
    @Override
    public void generateMap() {
        initCells(width, height);
        constraints = new Constraints(0.4, 0.65, 6, 10, 6, 12);
        List<GroundSpace> groundSpaces = setGroundSpaces();
        connectGroundSpaces(groundSpaces);
        GroundSpace gs = groundSpaces.get(rand.nextInt(groundSpaces.size()));
        currentCells[gs.x() + 2][gs.y() + 2] = CellType.EXIT;
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
     * See {@link MapBuilder#placeMobs()}, {@link MapBuilderCommons#placeMobs(CellType[][], MobFactory)}
     */
    @Override
    public void placeMobs() {
        factory = new MagicMobFactory();
        commons.placeMobs(currentCells, factory);
        factory = new TechMobFactory();
        commons.placeMobs(currentCells, factory);
        factory = new NatureMobFactory();
        commons.placeMobs(currentCells, factory);
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

    private void initCells(int width, int height) {
        currentCells = new CellType[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                currentCells[i][j] = CellType.OBSTACLE;
            }
        }
    }

    /**
     * Generates distinct coordinated for ground spaces, sets random sizes to each one and fills the cells accordingly
     *
     * @return list of all generated ground spaces
     */
    private List<GroundSpace> setGroundSpaces() {
        int groundsAcross = currentCells.length / constraints.maxW();
        int groundsDown = currentCells[0].length / constraints.maxH();
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
            ground.setCells(currentCells);
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
                    currentCells[curX][curY] = CellType.GROUND;
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
}
