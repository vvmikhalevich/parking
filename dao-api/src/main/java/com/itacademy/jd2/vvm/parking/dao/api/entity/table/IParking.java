package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

import java.math.BigDecimal;

public interface IParking extends IBaseEntity {

	String getName();

	void setName(String name);

	String getAdress();

	void setAdress(String adress);

	Integer getWidth();

	void setWidth(Integer width);

	Integer getLength();

	void setLength(Integer length);

	BigDecimal getCostPerDay();

	void setCostPerDay(BigDecimal costPerDay);

	String getStatus();

	void setStatus(String status);

}
