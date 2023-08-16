package com.cinema.galaxy.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errors = getErrors(ex.getBindingResult().getAllErrors());
        return createErrorResponse(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errors = getErrors(ex.getConstraintViolations());
        return createErrorResponse(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        return createErrorResponse(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        return createErrorResponse(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        List<String> errors = new ArrayList<>();
        errors.add("סיסמא לא נכונה."); // TODO: improve
        return createErrorResponse(errors, HttpStatus.UNAUTHORIZED);
    }

    private ResponseEntity<Object> createErrorResponse(List<String> errors, HttpStatus httpStatus) {
        Map<String, Object> body = new HashMap<>();
        body.put("errors", errors);
        return new ResponseEntity<>(body, httpStatus);
    }

    private List<String> getErrors(List<ObjectError> objectErrors) {
        return objectErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }

    private List<String> getErrors(Iterable<? extends ConstraintViolation<?>> constraintViolations) {
        return StreamSupport.stream(constraintViolations.spliterator(), false)
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    }
}