package com.game.monopoly.mapper;

import com.game.monopoly.exception.data.ApiError;
import org.springframework.http.ResponseEntity;

public class ResponseErrorMapper {

    public static ResponseEntity errorToEntity(ApiError error) {
        return new ResponseEntity(error, error.getError());
    }
}
