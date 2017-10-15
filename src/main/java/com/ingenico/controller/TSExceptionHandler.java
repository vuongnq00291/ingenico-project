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
    	TSResponse res = new TSResponse();
    	if(ex instanceof TSException){
    		TSException e = (TSException) ex;
    		res.setError(e.getError());
    	}else{
    		res.setError(ex.getLocalizedMessage());
    	}
    	res.setStatus(0);
        return new ResponseEntity<TSResponse>(res, new HttpHeaders(), HttpStatus.OK);
}
}