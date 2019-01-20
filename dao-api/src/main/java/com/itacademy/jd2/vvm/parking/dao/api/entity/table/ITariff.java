package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

import java.math.BigDecimal;

import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.TariffType;

public interface ITariff extends IBaseEntity {

	String getName();

	void setName(String name);

	BigDecimal getCostPerDay();

	void setCostPerDay(BigDecimal costPerDay);

	TariffType getStatus();

	void setStatus(TariffType status);

}
