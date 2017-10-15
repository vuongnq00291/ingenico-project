package com.ingenico.service;

import com.ingenico.model.Account;

public interface AccountManagementService {
	Account get(int id) throws Exception;
	void create(Account account) throws Exception;
}
