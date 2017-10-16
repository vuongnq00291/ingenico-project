package com.ingenico.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenico.cache.GlobalCache;
import com.ingenico.model.Account;
import com.ingenico.model.TSException;
import com.ingenico.service.ConcurrentService;
import com.ingenico.service.MoneyManagementService;

@Service
public class ConcurrentServiceImpl  implements ConcurrentService{
	@Autowired
	private MoneyManagementService moneyManagementService;
	@Autowired
	private GlobalCache cache;
	public void trasfer(int fromAccountId, int toAccountId, double amount) throws Exception {
		Account from = cache.getLock(fromAccountId);
		Account to = cache.getLock(toAccountId);
		checkNullAccount(from, to, fromAccountId,toAccountId);
		//TO avoid deadlock make sure we always lock accounts in same order.
		Account first = from;
	    Account second = to;
		if (first.getId()<second.getId()) {
	          first = to;
	          second = from;
	    }
		synchronized(first){
			synchronized(second){
				moneyManagementService.trasfer(fromAccountId,  toAccountId,  amount);
			}
		}
	}
	
	private void checkNullAccount(Account from, Account to,int fromid,int toid) throws TSException {
		if(from==null){
			throw new TSException("Account id "+fromid +"does not exist.");
		}
		if(to==null){
			throw new TSException("Account id "+toid +"does not exist.");
		}
		if(from.getId() == to.getId()){
			throw new TSException("Can not transfer for itself.");
		}
	}
}
