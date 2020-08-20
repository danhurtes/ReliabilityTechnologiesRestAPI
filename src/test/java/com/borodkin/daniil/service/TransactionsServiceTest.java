package com.borodkin.daniil.service;

import com.borodkin.daniil.domain.Codes;
import com.borodkin.daniil.domain.Transactions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionsServiceTest {
	@Autowired
	private TransactionsService transactionsService;

	@Autowired
	private CodesRepo codesRepo;

	@Test
	public void isTransactionExists() {
		Codes code = new Codes(1, 10);
		this.codesRepo.save(code);
		Transactions transaction = new Transactions(1, code, "NEW", new Timestamp(System.currentTimeMillis()), 123574);
		this.transactionsService.apply(transaction);
		assertTrue(!this.transactionsService.isTransactionExists(transaction.getStatus(), transaction.getContractNumber()).isEmpty());
	}
}
