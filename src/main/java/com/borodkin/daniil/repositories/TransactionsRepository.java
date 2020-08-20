package com.borodkin.daniil.repositories;

import com.borodkin.daniil.domain.Codes;
import com.borodkin.daniil.domain.Transactions;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Crud operations.
 * findByCode - searching transaction by given code(Codes type).
 * findByStatusAndContractNumber -  searching list of transactions by given status and contract number.
 *
 * @author borodkin
 * @since 19.08.2020
 */

public interface TransactionsRepository extends CrudRepository<Transactions, Integer> {
	Transactions findByCode(Codes code);

	List<Transactions> findByStatusAndContractNumber(String statuss, int contract_number);
}
