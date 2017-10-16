package com.ingenico.service.intergration;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.ingenico.model.Account;
import com.ingenico.model.TSException;
import com.ingenico.service.AccountManagementService;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class AccountManagementServiceImplTest {
   @Autowired(required=true)
   private AccountManagementService accountService;
   @Test
   public void a_CreateOK() throws Exception{
	   	Account accout = new Account();
	   	accout.setBalance(100);
	   	accout.setName("test3"); 
		accountService.create(accout);
		accout= accountService.get(1);
		assertEquals(accout.getBalance(),new Double(100), 0.0);
		assertEquals(accout.getName(),"test3");
	    
   }
   @Test(expected = TSException.class)
   public void b_createSameName() throws Exception{
	   Account accout = new Account();
	   	accout.setBalance(100);
	   	accout.setName("test3");
		accountService.create(accout);
		accountService.create(accout);
   }
   
   @Test(expected = TSException.class)
   public void c_createNevagiveBalance() throws Exception{
	   Account accout = new Account();
	   	accout.setBalance(-100);
	   	accout.setName("test3");
		accountService.create(accout);
   }
}
