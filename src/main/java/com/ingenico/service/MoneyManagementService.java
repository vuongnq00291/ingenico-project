package com.ingenico.service;

public interface MoneyManagementService {
  public void trasfer(int fromAccountId, int toAccountId, double amount)throws Exception ;
}
