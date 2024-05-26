package com.sis.retro.exception;


import com.sis.retro.response.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ErrorResponse handleHandlerMethodValidationException(HandlerMethodValidationException ex) {
        ErrorResponse.ErrorResponseBuilder builder = ErrorResponse.builder();
        return builder.status(BAD_REQUEST.value()).message("accepted date format is dd/mm/yyyy")
                .detailedMessages(List.of(ex.getReason())).build();
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handleException(Exception ex) {
        ErrorResponse.ErrorResponseBuilder builder = ErrorResponse.builder();
        if (ex instanceof DataIntegrityViolationException) {
            builder = builder.message("retro name is not found");
        } else {
            builder = builder.message("internal error");
        }
        return builder.status(INTERNAL_SERVER_ERROR.value()).detailedMessages(List.of(ex.getMessage())).build();
    }

    private ErrorResponse processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
        return new ErrorResponse(BAD_REQUEST.value(), "validation error", fieldErrors.stream().
                map(eachError -> String.format("%s for the field %s", eachError.getDefaultMessage(), eachError.getField())).toList());
    }


}
