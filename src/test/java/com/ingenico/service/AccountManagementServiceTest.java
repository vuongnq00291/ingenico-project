package com.ingenico.service;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ingenico.model.Account;
import com.ingenico.model.TSException;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountManagementServiceTest {
   @Autowired(required=true)
   private MoneyManagementService moneyManagementService;
   @Autowired
   private AccountManagementService accountService;
   @Test
   public void a_transferOK() throws Exception{
	   	Account from = new Account();
		from.setId(3);
		from.setBalance(100);
		from.setName("test3");
		accountService.create(from);
		Account to = new Account();
		to.setId(2);
		to.setBalance(100);
		to.setName("test2");
	    accountService.create(to);
	    moneyManagementService.trasfer(1, 2, 100);
	    from= accountService.get(1);
	    to = accountService.get(2);
	    assertEquals(from.getBalance(), new Double(0).doubleValue(),0.0);
	    
   }
   @Test(expected = TSException.class)
   public void b_transferError() throws Exception{
	   	Account from = new Account();
		from.setBalance(50);
		from.setName("test3");
		accountService.create(from);
		Account to = new Account();
		to.setBalance(100);
		to.setName("test2");
	    accountService.create(to);
	    moneyManagementService.trasfer(1, 2, 100);
   }
}
