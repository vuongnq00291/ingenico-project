package com.ingenico.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ingenico.dao.AccountManagementDAO;
import com.ingenico.model.Account;

@Repository
public class AccountManagementDAOImpl implements AccountManagementDAO{
    @PersistenceContext
    private EntityManager entityManager;
    
    public Account get(int id) {
        return entityManager.find(Account.class, id);
    }
    @SuppressWarnings("unchecked")
	public Account getByName(String name) {
    	Query query  =  entityManager.createQuery("from Account where name=:name");
    	query.setParameter("name", name);
    	List<Account> result = query.getResultList();
    	if(result.size()>0){
    		return result.get(0);
    	}
        return null;
    }
    public void create(Account account) {
        entityManager.persist(account);
    }
    public void update(Account account) {
        entityManager.merge(account);
    }
	public void delete(int id) {
		Account account = get(id);
		entityManager.remove(account);
	}

}
