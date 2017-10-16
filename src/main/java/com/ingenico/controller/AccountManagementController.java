package com.ingenico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ingenico.annotation.TSController;
import com.ingenico.cache.GlobalCache;
import com.ingenico.model.Account;
import com.ingenico.model.TSResponse;
import com.ingenico.service.AccountManagementService;

@TSController
public class AccountManagementController {
	@Autowired
	private AccountManagementService accountService;
	@Autowired
	private GlobalCache cache;
	@RequestMapping(value = "/account",method=RequestMethod.POST)
	public ResponseEntity<TSResponse> create(@RequestBody Account account) throws Exception{
		accountService.create(account);
		cache.addLock(account.getId(),account);
		return new ResponseEntity<TSResponse>(new TSResponse(),HttpStatus.OK);
	}
	
}
