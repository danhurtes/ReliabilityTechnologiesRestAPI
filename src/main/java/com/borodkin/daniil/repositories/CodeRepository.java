package com.borodkin.daniil.repositories;

import com.borodkin.daniil.domain.Codes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Crud operations and findByCode - getting list of codes by given code.
 *
 * @author borodkin
 * @since 19.08.2020
 */

public interface CodeRepository extends CrudRepository<Codes, Integer> {
	List<Codes> findByCode(int code);
}
