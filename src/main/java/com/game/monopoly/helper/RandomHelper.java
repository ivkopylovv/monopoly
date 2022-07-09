package com.game.monopoly.helper;

import java.util.Random;

public class RandomHelper {

    public static int getRandomDiceValue(int min, int max) {
        return new Random().nextInt(max + 1 - min) + min;
    }
}