package com.game.monopoly.exception.handler;

import com.game.monopoly.exception.ResourceAlreadyExistsException;
import com.game.monopoly.exception.ResourceNotFoundException;
import com.game.monopoly.exception.data.ApiError;
import com.game.monopoly.helper.DateHelper;
import com.game.monopoly.helper.ServletPathHelper;
import com.game.monopoly.mapper.ResponseErrorMapper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

import static com.game.monopoly.constants.GlobalErrorDetail.*;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException e, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        StringBuilder message = new StringBuilder();

        message.append(e.getContentType()).append(MEDIA_TYPE_NOT_SUPPORTED);
        e.getSupportedMediaTypes().forEach(t -> message.append(t).append(", "));

        ApiError error = new ApiError(
                DateHelper.getCurrentDate(),
                UNSUPPORTED_MEDIA_TYPE.value(),
                UNSUPPORTED_MEDIA_TYPE,
                message.toString(),
                ServletPathHelper.getServletPath(request)
        );

        return ResponseErrorMapper.errorToEntity(error);
    }

    @Override
    protected ResponseEntity handleHttpMessageNotReadable(
            HttpMessageNotReadableException e, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        ApiError error = new ApiError(
                DateHelper.getCurrentDate(),
                BAD_REQUEST.value(),
                BAD_REQUEST,
                e.getMessage(),
                ServletPathHelper.getServletPath(request)
        );

        return ResponseErrorMapper.errorToEntity(error);
    }

    @Override
    protected ResponseEntity handleMethodArgumentNotValid(
            MethodArgumentNotValidException e, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getObjectName() + " : " + error.getDefaultMessage())
                .toString();

        ApiError error = new ApiError(
                DateHelper.getCurrentDate(),
                BAD_REQUEST.value(),
                BAD_REQUEST,
                message,
                ServletPathHelper.getServletPath(request)
        );

        return ResponseErrorMapper.errorToEntity(error);
    }

    @Override
    protected ResponseEntity handleMissingServletRequestParameter(
            MissingServletRequestParameterException e, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String message = e.getParameterName() + PARAMETER_IS_MISSING;

        ApiError error = new ApiError(
                DateHelper.getCurrentDate(),
                BAD_REQUEST.value(),
                BAD_REQUEST,
                message,
                ServletPathHelper.getServletPath(request)
        );

        return ResponseErrorMapper.errorToEntity(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException e, WebRequest request) {
        ApiError error = new ApiError(
                DateHelper.getCurrentDate(),
                BAD_REQUEST.value(),
                BAD_REQUEST,
                e.getMessage(),
                ServletPathHelper.getServletPath(request)
        );

        return ResponseErrorMapper.errorToEntity(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(
            ConstraintViolationException e, WebRequest request) {
        ApiError error = new ApiError(
                DateHelper.getCurrentDate(),
                BAD_REQUEST.value(),
                BAD_REQUEST,
                e.getMessage(),
                ServletPathHelper.getServletPath(request)
        );

        return ResponseErrorMapper.errorToEntity(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFoundException(
            ResourceNotFoundException e, WebRequest request) {
        ApiError error = new ApiError(
                DateHelper.getCurrentDate(),
                BAD_REQUEST.value(),
                BAD_REQUEST,
                e.getMessage(),
                ServletPathHelper.getServletPath(request)
        );

        return ResponseErrorMapper.errorToEntity(error);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity handleResourceNotFoundException(
            ResourceAlreadyExistsException e, WebRequest request) {
        ApiError error = new ApiError(
                DateHelper.getCurrentDate(),
                BAD_REQUEST.value(),
                BAD_REQUEST,
                e.getMessage(),
                ServletPathHelper.getServletPath(request)
        );

        return ResponseErrorMapper.errorToEntity(error);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleResourceNotFoundException(
            IOException e, WebRequest request) {
        ApiError error = new ApiError(
                DateHelper.getCurrentDate(),
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR,
                e.getMessage(),
                ServletPathHelper.getServletPath(request)
        );

        return ResponseErrorMapper.errorToEntity(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleResourceNotFoundException(
            RuntimeException e, WebRequest request) {
        ApiError error = new ApiError(
                DateHelper.getCurrentDate(),
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR,
                e.getMessage(),
                ServletPathHelper.getServletPath(request)
        );

        return ResponseErrorMapper.errorToEntity(error);
    }

    @Override
    protected ResponseEntity handleNoHandlerFoundException(
            NoHandlerFoundException e, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String message = String.format(NOT_FOUND_METHOD_FOR_URL, e.getHttpMethod(), e.getRequestURL());

        ApiError error = new ApiError(
                DateHelper.getCurrentDate(),
                BAD_REQUEST.value(),
                BAD_REQUEST,
                message,
                ServletPathHelper.getServletPath(request)
        );

        return ResponseErrorMapper.errorToEntity(error);
    }

}
