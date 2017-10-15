package com.ingenico.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ingenico.dao.AccountManagementDAO;
import com.ingenico.model.Account;
import com.ingenico.model.TSException;

@Service
public class AccountManagementServiceImpl {
	
	@Autowired
	private AccountManagementDAO accountDAO;
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
    public void create(Account account) throws Exception {
		Account temp= accountDAO.getByName(account.getName());
		if(temp!=null){
			throw new TSException("Account name does exist.");
		}
		if(account.getBalance()<0){
			throw new TSException("Balance must be positive number.");
		}
		accountDAO.create(account);
    }
	
}