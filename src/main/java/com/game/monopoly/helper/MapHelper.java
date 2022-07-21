package com.game.monopoly.helper;

import com.game.monopoly.dto.response.CardStateDTO;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapHelper {

    public static Map<Long, CardStateDTO> mergeTwoMaps(Map<Long, CardStateDTO> first, Map<Long, CardStateDTO> second) {
        return Stream.of(first, second)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue));
    }
}
