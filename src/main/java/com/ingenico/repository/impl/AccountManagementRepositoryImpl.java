package com.ingenico.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ingenico.model.Account;
import com.ingenico.repository.AccountManagementRepository;

@Repository
public class AccountManagementRepositoryImpl implements AccountManagementRepository{
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
