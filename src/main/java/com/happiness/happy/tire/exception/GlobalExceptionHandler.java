package com.happiness.happy.tire.exception;

import com.happiness.happy.tire.entity.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice()
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Response handlerException(Exception e) {
        String eName = e.getClass().getSimpleName();
        log.error("系统异常：{}", eName);
        return Response.builder().code(500).msg("系统异常").object(e.getMessage()).build();
    }
}
