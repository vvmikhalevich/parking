package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

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

	ParkingType getStatus();

	void setStatus(ParkingType status);

}
