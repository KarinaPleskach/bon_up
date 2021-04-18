package com.bsuir.diploma.bonup.exception.handler;

import com.bsuir.diploma.bonup.exception.BaseException;
import com.bsuir.diploma.bonup.service.translation.TranslationService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private TranslationService translationService;

    @ExceptionHandler({BaseException.class})
    protected ResponseEntity<ResponseException> handleValidationException(BaseException e) {
        String message = translationService.getMessage(e.getKey(), e.getLang());
        return new ResponseEntity<>(new ResponseException(message, false), HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    private static class ResponseException {
        private String message;
        private Boolean isSuccess;
    }

}
