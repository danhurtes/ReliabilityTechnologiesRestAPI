package com.borodkin.daniil.service;

import com.borodkin.daniil.domain.Transactions;

import java.util.List;

/**
 * save/update operations.
 * Checking whether transaction exists by status & contractNumber.
 * Getting transaction by code and its current id
 *
 * @author borodkin
 * @since 19.08.2020
 */

public interface TransactionsService {
	Transactions apply(Transactions transactions);

	void update(Transactions transactions);

	Transactions getTransactionByCode(int id, int code);

	List<Transactions> isTransactionExists(String status, int contractNumber);
}
