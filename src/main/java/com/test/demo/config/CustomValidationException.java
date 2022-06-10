package com.test.demo.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomValidationException extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, 
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        //body.put("code", HttpStatus.BAD_REQUEST);
        body.put("status", false);
        body.put("message", "Validation errors");

        Map mError = new HashMap();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            List<String> er = new ArrayList<>();
            if(mError.get(fieldName)!=null){
                er = (ArrayList) mError.get(fieldName);
            }

            er.add(errorMessage);
            mError.put(fieldName, er);
        });

        body.put("errors", mError);
        return new ResponseEntity<>(body, headers, status);
    }
}
