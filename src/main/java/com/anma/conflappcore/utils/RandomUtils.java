package com.anma.conflappcore.utils;

import java.util.Random;

public class RandomUtils {

    public static int getRandomNum(int from, int to) {
        return new Random().nextInt(from, to);
    }
}
