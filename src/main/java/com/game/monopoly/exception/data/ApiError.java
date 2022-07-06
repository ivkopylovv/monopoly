package com.game.monopoly.exception.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static com.game.monopoly.constants.DateFormat.API_ERROR_FORMAT;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {

    @JsonFormat(shape = STRING, pattern = API_ERROR_FORMAT)
    private Date timestamp;
    private Integer status;
    private HttpStatus error;
    private String message;
    private String path;
}
