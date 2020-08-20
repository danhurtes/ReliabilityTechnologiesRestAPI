package com.borodkin.daniil.service;

import com.borodkin.daniil.domain.Codes;
import com.borodkin.daniil.repositories.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {
	private CodeRepository codeRepository;

	@Autowired
	public CodeServiceImpl(CodeRepository codeRepository) {
		this.codeRepository = codeRepository;
	}

	@Override
	public boolean isCodeExists(int code) {
		return !this.codeRepository.findByCode(code).isEmpty();
	}

	@Override
	public List<Codes> getCodes(int code) {
		return this.codeRepository.findByCode(code);
	}
}
