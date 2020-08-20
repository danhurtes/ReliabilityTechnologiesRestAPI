package com.borodkin.daniil.service;

import com.borodkin.daniil.domain.Codes;

import java.util.List;

/**
 * Receiving info about codes table.
 * Checking whether code exists by given in http-request code.
 * Getting all existing codes by code
 *
 * @author borodkin
 * @since 19.08.2020
 */

public interface CodeService {
	boolean isCodeExists(int code);

	List<Codes> getCodes(int code);
}
