package com.ingenico.service;

import com.ingenico.model.TrasferInfo;

public interface ConcurrentService {
	void trasfer(int fromAccountId, int toAccountId, double amount) throws Exception;
}
