package com.ingenico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ingenico.annotation.TSController;
import com.ingenico.cache.GlobalCache;
import com.ingenico.model.TSException;
import com.ingenico.model.TSResponse;
import com.ingenico.model.TrasferInfo;
import com.ingenico.service.MoneyManagementService;

@TSController
public class MoneyManagementController {

	@Autowired
	private MoneyManagementService moneyManagementService;
	@Autowired
	private GlobalCache cache;
	@RequestMapping(value = "/transfer",method=RequestMethod.POST)
	public ResponseEntity<TSResponse> transfer(@RequestBody TrasferInfo info) throws Exception{
		Object locktoaccount = cache.getLock(info.getToAccountId());
		Object lockfromaccount = cache.getLock(info.getFromAccountId());
		if(locktoaccount==null){
			throw new TSException("Account id "+info.getToAccountId() +"does not exist.");
		}
		if(lockfromaccount==null){
			throw new TSException("Account id "+info.getFromAccountId() +"does not exist.");
		}
		synchronized(lockfromaccount){
			synchronized(locktoaccount){
				System.out.println("start");
				moneyManagementService.trasfer(info.getFromAccountId(), info.getToAccountId(), info.getAmount());
				System.out.println("end");
			}
		}
		return new ResponseEntity<TSResponse>(new TSResponse(),HttpStatus.OK);
	}
}
