package com.ingenico.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.ingenico.model.TSException;
import com.ingenico.model.TSResponse;

@ControllerAdvice
public class TSExceptionHandler {
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<TSResponse> handleAll(final Exception ex, final WebRequest request) {
    	String error = "";
    	if(ex instanceof TSException){
    		TSException e = (TSException) ex;
    		error = e.getError();
    	}else{
    		error = ex.getLocalizedMessage();
    	}
        return new ResponseEntity<TSResponse>(new TSResponse(0, error), new HttpHeaders(), HttpStatus.OK);
}
}