package com.borodkin.daniil.service;

import com.borodkin.daniil.domain.Codes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeServiceTest {
	@Autowired
	private CodeService codeService;

	@Autowired
	private CodesRepo codesRepo;

	@Test
	public void isCodeExists() {
		Codes code = new Codes(1, 10);
		this.codesRepo.save(code);
		assertTrue(this.codeService.isCodeExists(code.getCode()));
	}
}
