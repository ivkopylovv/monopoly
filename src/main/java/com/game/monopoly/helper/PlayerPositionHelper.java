package com.game.monopoly.helper;

import static com.game.monopoly.constants.PlayingFieldParam.FIELD_MIN_SIZE;
import static com.game.monopoly.constants.PlayingFieldParam.FIELD_SIZE;

public class PlayerPositionHelper {

    public static int getNewPosition(int playerPos, int firstRoll, int secondRoll) {
        int potentialPos = playerPos + firstRoll + secondRoll;

        return potentialPos < FIELD_SIZE ? potentialPos : potentialPos - FIELD_SIZE;
    }

    public static int getNewPosition(int playerPos, int step) {
        int potentialPos = playerPos + step;

        if (potentialPos < FIELD_MIN_SIZE) {
            return FIELD_SIZE + potentialPos;
        } else if (potentialPos > FIELD_SIZE) {
            return potentialPos - FIELD_SIZE;
        } else {
            return potentialPos;
        }
    }
}
