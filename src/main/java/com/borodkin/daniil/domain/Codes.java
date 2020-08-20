package com.borodkin.daniil.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * Codes entity
 *
 * @author borodkin
 * @since 19.08.2020
 */

@Entity(name = "codes")
public class Codes implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	private int code;

	@OneToMany(mappedBy = "code", cascade = CascadeType.ALL)
	private Collection<Transactions> transactions;

	public Codes() {
	}

	public Codes(int code) {
		this();
		this.code = code;
	}

	public Codes(int id, int code) {
		this();
		this.id = id;
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Codes codes = (Codes) o;
		return id == codes.id &&
		       code == codes.code;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, code);
	}
}
