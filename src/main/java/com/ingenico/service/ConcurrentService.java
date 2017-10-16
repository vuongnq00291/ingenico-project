package com.ingenico.service;

public interface ConcurrentService {
	void trasfer(int fromAccountId, int toAccountId, double amount) throws Exception;
}
