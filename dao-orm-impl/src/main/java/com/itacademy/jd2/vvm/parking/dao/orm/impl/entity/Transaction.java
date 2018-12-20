package com.itacademy.jd2.vvm.parking.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IClient;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITransaction;

@Entity
public class Transaction extends BaseEntity implements ITransaction {

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Client.class)
	private IClient client;

	@Column
	private Integer value;

	@Column
	private String description;

	@Override
	public IClient getClient() {
		return client;
	}

	@Override
	public void setClient(IClient client) {
		this.client = client;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public void setValue(Integer value) {
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
