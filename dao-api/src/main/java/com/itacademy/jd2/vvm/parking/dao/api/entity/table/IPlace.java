package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

public interface IPlace extends IBaseEntity {

	String getName();

	void setName(String name);

	IParking getParking();

	void setParking(IParking parking);

	ICar getCar();

	void setCar(ICar car);

	IUserAccount getUserAccount();

	void setUserAccount(IUserAccount user);

}
