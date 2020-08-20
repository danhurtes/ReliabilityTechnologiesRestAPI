package com.borodkin.daniil.service;

import com.borodkin.daniil.domain.Transactions;
import org.springframework.data.repository.CrudRepository;

public interface TransactRepo extends CrudRepository<Transactions, Integer> {
}
