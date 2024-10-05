package co.pragra.learning.newproductmanager.controller;

import co.pragra.learning.newproductmanager.error.ErrorDto;
import co.pragra.learning.newproductmanager.exception.InvalidReviewException;
import co.pragra.learning.newproductmanager.exception.InvalidUserDataException;
import co.pragra.learning.newproductmanager.exception.ReviewNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
@ControllerAdvice

public class ErrorController {

    @ExceptionHandler(InvalidReviewException.class)
    public ResponseEntity<ErrorDto> handledBadReview(Exception ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDto.builder()
                .errorCode(404).errorMessage(ex.getMessage()).timestamp(new Date()).build());
    }

    @ExceptionHandler(InvalidUserDataException.class)
    public ResponseEntity<ErrorDto> handledBadUser(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDto.builder()
                .timestamp(new Date()).errorMessage(ex.getMessage()).errorCode(400).build());
    }
    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorDto> handleNotFound(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDto.builder()
                .timestamp(new Date()).errorMessage(ex.getMessage()).errorCode(400).build());
    }
}
