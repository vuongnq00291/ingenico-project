package com.ingenico.dao;

import com.ingenico.model.Account;

public interface AccountManagementDAO {
	Account getByName(String name) ;
	Account get(int id) throws Exception;
	void create(Account account) throws Exception;
    void update(Account account) throws Exception;
    void delete(int id) throws Exception;
}
