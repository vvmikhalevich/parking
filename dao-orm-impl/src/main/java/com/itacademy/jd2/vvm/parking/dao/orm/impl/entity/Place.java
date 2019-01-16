package com.itacademy.jd2.vvm.parking.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IParking;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;

@Entity
public class Place extends BaseEntity implements IPlace {

	@Column
	private String name;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Parking.class)
	private IParking parking;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Car.class)
	private ICar car;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public IParking getParking() {
		return parking;
	}

	@Override
	public void setParking(IParking parking) {
		this.parking = parking;
	}

	@Override
	public ICar getCar() {
		return car;
	}

	@Override
	public void setCar(ICar car) {
		this.car = car;
	}

}
