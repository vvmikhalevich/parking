package com.itacademy.jd2.vvm.parking.dao.orm.impl.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.TariffType;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;

@Entity
public class Tariff extends BaseEntity implements ITariff {

	@Column
	private String name;

	@Column
	private BigDecimal costPerDay;

	@Column
	@Enumerated(EnumType.STRING)
	private TariffType status;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public BigDecimal getCostPerDay() {
		return costPerDay;
	}

	@Override
	public void setCostPerDay(BigDecimal costPerDay) {
		this.costPerDay = costPerDay;
	}

	@Override
	public TariffType getStatus() {
		return status;
	}

	@Override
	public void setStatus(TariffType status) {
		this.status = status;
	}

}
