package com.anma.conflappcore.utils;

import java.util.Random;

public class RandomUtils {

    public static int getRandomNum(int from, int to) {
        return new Random().nextInt(from, to);
    }

    public static String getRandomText(int length) {
        var text = "Lorem ipsum dolor sit amet consectetur adipiscing elit at risus, dignissim auctor himenaeos " +
                "facilisis duis nam hac per dui, felis leo penatibus bibendum placerat vivamus sem nisi";

        var arr = text.split(" ");
        var builder = new StringBuilder();

        for (int i = 0; i < arr.length - 1; i++) {
            builder.append(arr[getRandomNum(0, arr.length - 1)]).append(" ");
        }

        return builder.toString();
    }
}
