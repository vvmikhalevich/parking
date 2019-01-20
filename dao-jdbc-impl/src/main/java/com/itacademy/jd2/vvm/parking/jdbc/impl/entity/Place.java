package com.itacademy.jd2.vvm.parking.jdbc.impl.entity;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IParking;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;

public class Place extends BaseEntity implements IPlace {

	private String name;

	private IParking parking;

	private ICar car;

	private IUserAccount userAccount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IParking getParking() {
		return parking;
	}

	public void setParking(IParking parking) {
		this.parking = parking;
	}

	public ICar getCar() {
		return car;
	}

	public void setCar(ICar car) {
		this.car = car;
	}

	public IUserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
