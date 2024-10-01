package com.github.syndexmx.fisherstheorem.utils;

import java.util.Random;

public class MathUtils {

    static private Random random = new Random();

    static public int getRandom(int lowerThan) {
        return random.nextInt(lowerThan);
    }
}
