package com.borodkin.daniil.controllers;

import com.borodkin.daniil.domain.Codes;
import com.borodkin.daniil.domain.Transactions;
import com.borodkin.daniil.service.CodeService;
import com.borodkin.daniil.service.TransactionsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.TimeZone;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CodeController.class)
public class CodeControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private CodeService codeService;

	@MockBean
	private TransactionsService transactionsService;

	@Test
	public void whenApplyThenSave() throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		Codes code = new Codes(1, 10);
		Transactions transaction = new Transactions(code, "NEW", 123574);
		transaction.setTime(new Timestamp(System.currentTimeMillis()));

		given(this.codeService.isCodeExists(transaction.getCode().getCode())).willReturn(false);
		when(this.transactionsService.apply(transaction)).thenReturn(transaction);

		this.mvc.perform(
				post("/post").contentType(MediaType.APPLICATION_JSON_UTF8).content(
						mapper.writeValueAsString(
								transaction
						)
				)
		).andExpect(
				status().isCreated()
		);
	}
}
