package com.ingenico.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ingenico.model.Account;

@Component
public class GlobalCache {
	private Map<Integer,Account> accountLocks = new HashMap<Integer,Account>();
	public void addLock(int accountId,Account account){
		accountLocks.put(accountId, account);
	}
	public Account getLock(int accountid){
		return accountLocks.get(accountid);
	}
}
