package com.ingenico.dao;

import com.ingenico.model.Account;

public interface AccountManagementDAO {
	Account getByName(String name) ;
    void create(Account account);
    void update(Account account);
    void delete(int id);
}
