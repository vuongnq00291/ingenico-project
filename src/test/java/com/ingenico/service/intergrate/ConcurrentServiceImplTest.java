package com.ingenico.service.intergrate;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ingenico.cache.GlobalCache;
import com.ingenico.model.Account;
import com.ingenico.model.TrasferInfo;
import com.ingenico.service.AccountManagementService;
import com.ingenico.service.ConcurrentService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@RunWith(SpringRunner.class)
public class ConcurrentServiceImplTest {
	@Autowired
	private ConcurrentService concurrentService;
	@Autowired(required=true)
	private AccountManagementService accountService;
	@Autowired
	private GlobalCache cache;
	@Before
	public void init() throws Exception{
		Account from = new Account();
		from.setBalance(10000);
		from.setName("test3");
		accountService.create(from);
		Account to = new Account();
		to.setBalance(10000);
		to.setName("test2");
	    accountService.create(to);
	    cache.addLock(from.getId(), from);
	    cache.addLock(to.getId(), to);
	}
	
	@Test
	public void deadlockTest() throws Exception{
		final int numberThreads = 10;
		final ExecutorService executorService = Executors.newFixedThreadPool(numberThreads);
		final CountDownLatch count = new CountDownLatch(numberThreads);
		for(int i=0;i<numberThreads;i++){
			if(i%2==0){
				executorService.execute(new DataTest(count, concurrentService, 
						new TrasferInfo(1, 2, 200)));
			}
			if(i%2!=0){
				executorService.execute(new DataTest(count, concurrentService, 
						new TrasferInfo(2, 1, 100)));
			}
		}
		count.await();
		Account one = accountService.get(1);
		assertEquals(one.getBalance(), new Double(9500).doubleValue(),0.0);
		Account two = accountService.get(2);
		assertEquals(two.getBalance(), new Double(10500).doubleValue(),0.0);
		
	}
}
class DataTest implements Runnable{
	private CountDownLatch count;
	private ConcurrentService concurrentService;
	private TrasferInfo info;
	
	public DataTest(CountDownLatch count,ConcurrentService concurrentService,TrasferInfo info){
		this.concurrentService = concurrentService;
		this.info = info;
		this.count = count;
	}
	public void run() {
		try {
			concurrentService.trasfer(info.getFromAccountId(), info.getToAccountId(), info.getAmount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		count.countDown();
	}
	 
}