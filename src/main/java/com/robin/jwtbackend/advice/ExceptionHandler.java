package com.robin.jwtbackend.advice;

import com.robin.jwtbackend.dto.ErrorDto;
import com.robin.jwtbackend.exception.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler(value = {AppException.class})
  @ResponseBody
  public ResponseEntity<ErrorDto> handleException(AppException ex)
  {
     return ResponseEntity.status(ex.getHttpStatus())
             .body( new ErrorDto(ex.getMessage()) );
  }


}
