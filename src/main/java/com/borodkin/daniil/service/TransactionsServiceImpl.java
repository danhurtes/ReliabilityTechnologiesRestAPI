package com.borodkin.daniil.service;

import com.borodkin.daniil.domain.Codes;
import com.borodkin.daniil.domain.Transactions;
import com.borodkin.daniil.repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsServiceImpl implements TransactionsService {
	private TransactionsRepository transactionsRepository;

	@Autowired
	public TransactionsServiceImpl(TransactionsRepository transactionsRepository) {
		this.transactionsRepository = transactionsRepository;
	}

	@Override
	public Transactions apply(final Transactions transactions) {
		return this.transactionsRepository.save(transactions);
	}

	@Override
	public void update(final Transactions transactions) {
		this.transactionsRepository.save(transactions);
	}

	@Override
	public Transactions getTransactionByCode(int id, int code) {
		return this.transactionsRepository.findByCode(new Codes(id, code));
	}

	@Override
	public List<Transactions> isTransactionExists(String status, int contractNumber) {
		return this.transactionsRepository.findByStatusAndContractNumber(status, contractNumber);
	}
}
