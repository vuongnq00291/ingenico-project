package com.ingenico.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ingenico.SpringJPA.impl.AccountManagementSpringJPAImpl;
import com.ingenico.model.Account;
import com.ingenico.model.TSException;
import com.ingenico.service.MoneyManagementService;
@Repository
public class MoneyManagementServiceImpl implements MoneyManagementService {
	@Autowired
	private AccountManagementSpringJPAImpl accountDAOImpl;
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
    public void trasfer(int fromAccountId, int toAccountId, double amount) throws Exception {
		Account from = accountDAOImpl.get(fromAccountId);
		Account to = accountDAOImpl.get(toAccountId);
		if(from==null){
			throw new TSException("Account id "+fromAccountId +"does not exist.");
		}
		if(to==null){
			throw new TSException("Account id "+toAccountId+"does not exist.");
		}
		if(from.getBalance()<amount){
			throw new TSException("Account balance is insufficient for this transaction.");
		}
		from.setBalance(from.getBalance()-amount);
		to.setBalance(to.getBalance()+amount);
		accountDAOImpl.update(from);
		accountDAOImpl.update(to);
    }
}
