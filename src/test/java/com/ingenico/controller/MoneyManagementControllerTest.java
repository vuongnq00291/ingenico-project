package com.ingenico.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingenico.cache.GlobalCache;
import com.ingenico.model.Account;
import com.ingenico.model.TSResponse;
import com.ingenico.service.ConcurrentService;
import com.ingenico.service.MoneyManagementService;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
public class MoneyManagementControllerTest {
	private MockMvc mockMvc;
	@Mock
	private MoneyManagementService moneyManagementService;
	@Mock
	private GlobalCache cache;
	@Mock
	private ConcurrentService concurrentService;
	@InjectMocks
	private MoneyManagementController controller;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void transferTest() throws Exception {
		Account from = new Account();
		from.setId(3);
		from.setBalance(100);
		from.setName("test3");
		
		Account to = new Account();
		to.setId(2);
		to.setBalance(100);
		to.setName("test2");
		String object = "{\"fromAccountId\":3,\"toAccountId\":2,\"amount\":100.0}";
		when(cache.getLock(3)).thenReturn(from);
		when(cache.getLock(2)).thenReturn(to);
		MvcResult result = mockMvc.perform(post("/ingenico/transfer")
				.contentType(MediaType.APPLICATION_JSON).content(object))
				.andExpect(status().isOk())
				.andReturn();
		ObjectMapper mapper = new ObjectMapper();
		TSResponse res = mapper.readValue(result.getResponse().getContentAsString(),TSResponse.class);
		assertEquals(res.getError(), "Request successfull, no exception");
		assertEquals(res.getStatus(), 1);
	}


}
