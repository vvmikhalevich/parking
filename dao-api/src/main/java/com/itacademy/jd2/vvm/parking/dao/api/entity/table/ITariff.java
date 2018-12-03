package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

public interface ITariff extends IBaseEntity {

	String getName();

	void setName(String name);

	Integer getPrice();

	void setPrice(Integer price);

	String getStatus();

	void setStatus(String status);

}
