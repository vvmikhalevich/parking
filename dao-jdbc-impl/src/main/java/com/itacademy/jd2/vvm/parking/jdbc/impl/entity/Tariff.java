package com.itacademy.jd2.vvm.parking.jdbc.impl.entity;

import java.math.BigDecimal;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;

public class Tariff extends BaseEntity implements ITariff {

	private String name;

	private BigDecimal price;

	private String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
