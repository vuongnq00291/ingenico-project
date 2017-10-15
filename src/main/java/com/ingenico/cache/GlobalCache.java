package com.ingenico.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class GlobalCache {
	private Map<Integer,Object> accountLocks = new HashMap<Integer,Object>();
	public void addLock(int accountId){
		accountLocks.put(accountId, new Object());
	}
	public Object getLock(int accountid){
		return accountLocks.get(accountid);
	}
}
