package com.financial.transaction.redis.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RedisExceptionHandler {

    private Logger log = LoggerFactory.getLogger(RedisExceptionHandler.class);

    @ExceptionHandler(FinancialTransactionManegementException.class)
    public void handlePostNotFound(HttpServletRequest request, Exception ex) {
        log.error("{} : {}", ex.getMessage(), request.getRequestURI());
    }
}