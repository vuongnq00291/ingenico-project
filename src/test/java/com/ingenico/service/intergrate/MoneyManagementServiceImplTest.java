package com.ingenico.service.intergrate;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ingenico.cache.GlobalCache;
import com.ingenico.model.Account;
import com.ingenico.model.TSException;
import com.ingenico.service.AccountManagementService;
import com.ingenico.service.MoneyManagementService;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@RunWith(SpringRunner.class)
public class MoneyManagementServiceImplTest {
   @Autowired(required=true)
   private MoneyManagementService moneyManagementService;
   @Autowired
   private AccountManagementService accountService;
   @Autowired
   private GlobalCache cache;
   @Test
   public void a_transferOK() throws Exception{
	   	Account from = new Account();
		from.setBalance(100);
		from.setName("test3");
		accountService.create(from);
		Account to = new Account();
		to.setBalance(100);
		to.setName("test2");
	    accountService.create(to);
	    moneyManagementService.trasfer(1, 2, 50);
	    from= accountService.get(1);
	    to = accountService.get(2);
	    cache.addLock(from.getId(), from);
	    cache.addLock(to.getId(), to);
	    assertEquals(from.getBalance(), new Double(50).doubleValue(),0.0);
	    assertEquals(to.getBalance(), new Double(150).doubleValue(),0.0);
	    
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
