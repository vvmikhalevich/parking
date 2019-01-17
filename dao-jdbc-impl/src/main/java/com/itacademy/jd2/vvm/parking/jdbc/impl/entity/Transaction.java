package com.itacademy.jd2.vvm.parking.jdbc.impl.entity;

import java.math.BigDecimal;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;

public class Transaction extends BaseEntity implements ITransaction {

	private IUserAccount userAccount;

	private BigDecimal value;

	private String description;

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public IUserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
