package com.github.syndexmx.fisherstheorem.utils;

import java.util.Random;

public class MathUtils {

    static private Random random = new Random();

    static public int getRandom(int lowerThan) {
        return random.nextInt(lowerThan);
    }

    public static boolean getRandomBooleanWith(double probability) {
        double generatedDouble = (double)getRandom(1000000000) / 1000000000.0;
        return probability > generatedDouble;
    }
}
