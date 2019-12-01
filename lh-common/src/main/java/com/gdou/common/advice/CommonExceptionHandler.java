package com.gdou.common.advice;


import com.gdou.common.enums.ExceptionEnum;
import com.gdou.common.exception.LhException;
import com.gdou.common.vo.ExceptionResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(LhException.class)
    public ResponseEntity<ExceptionResult>handleException(LhException e){

        ExceptionEnum em=e.getExceptionEnum();
    return ResponseEntity.status(e.getExceptionEnum().getCode())
            .body(new ExceptionResult(e.getExceptionEnum()));
    }
}
