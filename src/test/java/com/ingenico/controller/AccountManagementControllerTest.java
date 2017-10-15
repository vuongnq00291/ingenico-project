package com.ingenico.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingenico.cache.GlobalCache;
import com.ingenico.model.TSResponse;
import com.ingenico.service.impl.AccountManagementServiceImpl;

@RunWith(SpringRunner.class)
public class AccountManagementControllerTest {
	private MockMvc mockMvc;
	@Mock
	private AccountManagementServiceImpl accountService;
	@Mock
	private GlobalCache cache;

	@InjectMocks
	private AccountManagementController resourceService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(resourceService).build();
	}

	@Test
	public void createAccountTest() throws Exception {
		
		String object = "{\"id\":1,\"balance\":100.0,\"name\":\"test1\"}";
		MvcResult result = mockMvc.perform(post("/ingenico/account")
				.contentType(MediaType.APPLICATION_JSON).content(object))
				.andExpect(status().isOk())
				.andReturn();
		ObjectMapper mapper = new ObjectMapper();
		TSResponse res = mapper.readValue(result.getResponse().getContentAsString(),TSResponse.class);
		assertEquals(res.getError(), "Request successfull, no exception");
		assertEquals(res.getStatus(), 1);
	}


}
