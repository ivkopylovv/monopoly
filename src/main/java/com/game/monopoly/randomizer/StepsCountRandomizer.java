package com.game.monopoly.randomizer;

import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor
public class StepsCountRandomizer {

    public static Long randomizeStepsCount(int min, int max) {
        return Long.valueOf(new Random().ints(min, (max + 1)).findFirst().getAsInt());
    }
}
