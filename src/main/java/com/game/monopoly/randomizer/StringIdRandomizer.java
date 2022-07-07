package com.game.monopoly.randomizer;


import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class StringIdRandomizer {
    public static String randomizeStringId() {
        return UUID.randomUUID().toString();
    }
}
