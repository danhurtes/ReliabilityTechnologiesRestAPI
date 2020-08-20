package com.borodkin.daniil.controllers;

import com.borodkin.daniil.domain.Codes;
import com.borodkin.daniil.domain.Transactions;
import com.borodkin.daniil.service.TransactionsService;
import com.borodkin.daniil.service.CodeService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

/**
 * Controller for processing requests
 * GetMapping(getTransitionsByCode) - getting transaction(-s) in json format
 * PostMapping(apply) - saving/updating transactions by json format
 *
 * @author borodkin
 * @since 19.08.2020
 */

@RestController
public class CodeController {
	private CodeService codeService;
	private TransactionsService transactionsService;

	@Autowired
	public CodeController(CodeService codeService, TransactionsService transactionsService) {
		this.codeService = codeService;
		this.transactionsService = transactionsService;
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Transactions> apply(@RequestBody Transactions transaction) {

		if (transaction == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Moscow"));
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());

		if (!codeService.isCodeExists(transaction.getCode().getCode())) {
			transaction.setTime(currentTime);
			transactionsService.apply(transaction);
			return new ResponseEntity<>(transaction, HttpStatus.CREATED);
		} else {
			if (!transactionsService.isTransactionExists(transaction.getStatus(), transaction.getContractNumber()).isEmpty()) {
				List<Codes> codes = codeService.getCodes(transaction.getCode().getCode());
				List<Transactions> allTransactions = Lists.newArrayList();
				codes.forEach(row -> allTransactions.add(transactionsService.getTransactionByCode(row.getId(), row.getCode())));
//				allTransactions.stream().filter(row ->
//						row.getStatus().equals(transaction.getStatus()) && row.getContractNumber() == transaction.getContractNumber())
//						.findFirst().ifPresent(trans -> {
//							trans.setTime(currentTime);
//							transactionsService.update(trans);
//						});
				Optional<Transactions> trans = allTransactions.stream().filter(row ->
						row.getStatus().equals(transaction.getStatus()) && row.getContractNumber() == transaction.getContractNumber())
						.findFirst();

				Transactions transact;
				if (trans.isPresent()) {
					transact = trans.get();
					transact.setTime(currentTime);
					transactionsService.update(transact);
					return new ResponseEntity<>(transact, HttpStatus.FOUND);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			} else {
				transaction.setTime(currentTime);
				transactionsService.apply(transaction);
				return new ResponseEntity<>(transaction, HttpStatus.CREATED);
			}
		}
	}

	@GetMapping(value = "{code}")
	public ResponseEntity<List<Transactions>> getTransitionsByCode(@PathVariable("code") int code) {
		List<Codes> codes = codeService.getCodes(code);
		List<Transactions> allTransactions = Lists.newArrayList();
		codes.forEach(row -> allTransactions.add(transactionsService.getTransactionByCode(row.getId(), row.getCode())));

		if (allTransactions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			//v1 - для получения только статусов
			//List<String> sts = allTransactions.stream().map(Transactions::getStatus).distinct().collect(toList());
			return new ResponseEntity<>(allTransactions, HttpStatus.FOUND);
		}
	}
}
