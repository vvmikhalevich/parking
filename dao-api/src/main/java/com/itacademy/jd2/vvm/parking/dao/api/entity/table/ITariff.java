package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

import java.math.BigDecimal;

public interface ITariff extends IBaseEntity {

	String getName();

	void setName(String name);

	BigDecimal getPrice();

	void setPrice(BigDecimal price);

	String getStatus();

	void setStatus(String status);

}
