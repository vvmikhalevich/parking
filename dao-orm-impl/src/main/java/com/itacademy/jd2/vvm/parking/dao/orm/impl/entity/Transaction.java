package com.itacademy.jd2.vvm.parking.dao.orm.impl.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;

@Entity
public class Transaction extends BaseEntity implements ITransaction {

	@OneToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
	private IUserAccount userAccount;

	@Column
	private BigDecimal value;

	@Column
	private String description;

	@Override
	public IUserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public BigDecimal getValue() {
		return value;
	}

	@Override
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

}
