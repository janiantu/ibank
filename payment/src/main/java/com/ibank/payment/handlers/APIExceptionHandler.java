package com.ibank.payment.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ibank.payment.dto.APIExceptionResponse;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
    

	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        APIExceptionResponse response = new APIExceptionResponse(
            status.toString(),
            status.getReasonPhrase(),
            e.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toCollection(ArrayList::new)));
        
        return ResponseEntity.badRequest().body(response);
    }

    @Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	APIExceptionResponse response = new APIExceptionResponse(
    			status.toString(),
                status.getReasonPhrase(),
                Arrays.asList(e.getMessage()));

        return ResponseEntity.badRequest().body(response);
    }   
    
    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<Object>  customHandleNotFound(Exception e, WebRequest request) {

    	APIExceptionResponse response = new APIExceptionResponse(
    			HttpStatus.NOT_FOUND.toString(),
    			HttpStatus.NOT_FOUND.getReasonPhrase(),
                Arrays.asList(e.getMessage()));

        return ResponseEntity.badRequest().body(response);

    }
    
    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<Object>  handleHttpMessageNotWritable(Exception e, WebRequest request) {

    	APIExceptionResponse response = new APIExceptionResponse(
    			HttpStatus.NOT_MODIFIED.toString(),
    			HttpStatus.NOT_MODIFIED.getReasonPhrase(),
                Arrays.asList(e.getMessage()));

        return ResponseEntity.badRequest().body(response);

    }

}
