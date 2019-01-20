package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

import java.math.BigDecimal;

import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.ParkingType;

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

	ParkingType getStatus();

	void setStatus(ParkingType status);

}
