package com.example.server;

import com.example.server.Exceptions.*;
import com.example.server.models.ErrorResultModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {TooMuchArgsException.class})
    public ResponseEntity<ErrorResultModel> TooMuchArgsException(TooMuchArgsException ex) {
        ErrorResultModel errorResultModel = new ErrorResultModel(ex.getMessage());
        return new ResponseEntity<ErrorResultModel>(errorResultModel, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {NotEnoughArgsException.class})
    public ResponseEntity<ErrorResultModel> NotEnoughArgsException(NotEnoughArgsException ex) {
        ErrorResultModel errorResultModel = new ErrorResultModel(ex.getMessage());
        return new ResponseEntity<ErrorResultModel>(errorResultModel, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {UnknownOperationException.class})
    public ResponseEntity<ErrorResultModel> UnknownOperationException(UnknownOperationException ex) {
        ErrorResultModel errorResultModel = new ErrorResultModel(ex.getMessage());
        return new ResponseEntity<ErrorResultModel>(errorResultModel, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {NegativeException.class})
    public ResponseEntity<ErrorResultModel> NegativeException(NegativeException ex) {
        ErrorResultModel errorResultModel = new ErrorResultModel(ex.getMessage());
        return new ResponseEntity<ErrorResultModel>(errorResultModel, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {DivisionException.class})
    public ResponseEntity<ErrorResultModel> DivisionException(DivisionException ex) {
        ErrorResultModel errorResultModel = new ErrorResultModel(ex.getMessage());
        return new ResponseEntity<ErrorResultModel>(errorResultModel, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResultModel> HttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorResultModel errorResultModel = new ErrorResultModel(ex.getMessage());
        return new ResponseEntity<ErrorResultModel>(errorResultModel, HttpStatus.CONFLICT);
    }
}

