package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

import java.math.BigDecimal;

import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.StatusType;

public interface ITariff extends IBaseEntity {

	String getName();

	void setName(String name);

	BigDecimal getPrice();

	void setPrice(BigDecimal price);

	StatusType getStatus();

	void setStatus(StatusType status);

}
