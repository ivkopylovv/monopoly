package com.game.monopoly.helper;

import java.util.Random;

import static com.game.monopoly.constants.PlayingFieldParam.MAX_BORDER;
import static com.game.monopoly.constants.PlayingFieldParam.MIN_BORDER;

public class RandomHelper {

    public static int getRandomDiceValue() {
        return new Random().nextInt(MAX_BORDER + 1 - MIN_BORDER) + MIN_BORDER;
    }

    public static Long getRandomCardId(Long max) {
        return new Random().nextLong(max) + 1;
    }
}
