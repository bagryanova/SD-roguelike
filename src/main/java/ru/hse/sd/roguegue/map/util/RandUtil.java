package ru.hse.sd.roguegue.map.util;

import java.util.Random;

/**
 * Util class for getting random values bounded by constraints
 */
class RandUtil {

    private RandUtil() {
    }

    private static final Random rand = new Random(System.currentTimeMillis());

    public static int getRandom(int lowerBound, int upperBound) {
        return (int) (rand.nextDouble() * ((upperBound - lowerBound) + 1)) + lowerBound;
    }
}
