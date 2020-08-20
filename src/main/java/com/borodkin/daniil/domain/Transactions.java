package com.borodkin.daniil.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Transactions entity
 *
 * @author borodkin
 * @since 19.08.2020
 */

@Entity(name = "transactions")
public class Transactions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public Transactions() {

	}

	public Transactions(Codes code, String status, Timestamp time, int contractNumber) {
		this();
		this.code = code;
		this.status = status;
		this.time = time;
		this.contractNumber = contractNumber;
	}

	public Transactions(Codes code, String status, int contractNumber) {
		this();
		this.code = code;
		this.status = status;
		this.contractNumber = contractNumber;
	}


	public Transactions(int id, Codes code, String status, Timestamp time, int contractNumber) {
		this();
		this.id = id;
		this.code = code;
		this.status = status;
		this.time = time;
		this.contractNumber = contractNumber;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "code_id")
	private Codes code;

	@Column(name = "statuss")
	private String status;
	private Timestamp time;

	@Column(name = "contract_number")
	private int contractNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Codes getCode() {
		return code;
	}

	public void setCode(Codes code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(int contractNumber) {
		this.contractNumber = contractNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Transactions that = (Transactions) o;
		return id == that.id &&
		       contractNumber == that.contractNumber &&
		       Objects.equals(code, that.code) &&
		       Objects.equals(status, that.status) &&
		       Objects.equals(time, that.time);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, code, status, time, contractNumber);
	}
}
