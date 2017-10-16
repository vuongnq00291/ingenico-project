package com.ingenico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ingenico.annotation.TSController;
import com.ingenico.model.TSResponse;
import com.ingenico.model.TrasferInfo;
import com.ingenico.service.ConcurrentService;

@TSController
public class MoneyManagementController {

	@Autowired
	private ConcurrentService concurrentService;
	@RequestMapping(value = "/transfer",method=RequestMethod.POST)
	public ResponseEntity<TSResponse> transfer(@RequestBody TrasferInfo info) throws Exception{
		concurrentService.trasfer(info.getFromAccountId(),info.getToAccountId(),info.getAmount());
		return new ResponseEntity<TSResponse>(new TSResponse(),HttpStatus.OK);
	}
	
}
