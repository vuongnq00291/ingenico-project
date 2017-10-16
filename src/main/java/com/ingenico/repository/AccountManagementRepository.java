package com.ingenico.repository;

import com.ingenico.model.Account;

public interface AccountManagementRepository {
	Account getByName(String name) ;
	Account get(int id) throws Exception;
	void create(Account account) throws Exception;
    void update(Account account) throws Exception;
    void delete(int id) throws Exception;
}
