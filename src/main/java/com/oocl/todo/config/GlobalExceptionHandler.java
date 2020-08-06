package com.oocl.todo.config;

import com.oocl.todo.constant.ExceptionMessage;
import com.oocl.todo.exception.NoSuchDataException;
import com.oocl.todo.exception.ParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchDataException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNoSuchDataException() {
        return ExceptionMessage.NO_SUCH_DATA.getMessage();
    }

    @ExceptionHandler(ParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleParameterException() {
        return ExceptionMessage.ERROR_PARAMETER.getMessage();
    }
}
