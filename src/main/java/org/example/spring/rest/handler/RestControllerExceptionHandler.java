package org.example.spring.rest.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice(basePackages = "org.example.spring.rest")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class) // default - all exception
    private void handleExceptions(Exception exception, HttpServletRequest request) {
        log.error("failed to return response", exception);
    }
}
