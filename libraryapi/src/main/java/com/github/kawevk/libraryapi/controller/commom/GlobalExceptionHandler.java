package com.github.kawevk.libraryapi.controller.commom;

import com.github.kawevk.libraryapi.dto.ErrorAnswer;
import com.github.kawevk.libraryapi.dto.ErrorField;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorAnswer handArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrorList =  e.getFieldErrors();
        List<ErrorField> errorsList = fieldErrorList.stream().map(fe -> new ErrorField(fe.getField(), fe.getDefaultMessage())).collect(Collectors.toList());

        return ErrorAnswer.unprocessableEntityAnswer("Validation failed", errorsList);
    }
}
