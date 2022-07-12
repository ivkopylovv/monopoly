package com.game.monopoly.helper;

import static com.game.monopoly.constants.PlayingFieldParam.FIELD_SIZE;

public class PlayerPositionHelper {

    public static int getNewPosition(int playerPos, int firstRoll, int secondRoll) {
        int potentialPos = playerPos + firstRoll + secondRoll;

        return potentialPos < FIELD_SIZE ? potentialPos : potentialPos - FIELD_SIZE;
    }
}
