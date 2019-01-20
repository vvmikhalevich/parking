package com.itacademy.jd2.vvm.parking.dao.orm.impl.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.ParkingType;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IParking;

@Entity
public class Parking extends BaseEntity implements IParking {

	@Column
	private String name;

	@Column
	private String adress;

	@Column
	private Integer width;

	@Column
	private Integer length;

	@Column
	@Enumerated(EnumType.STRING)
	private ParkingType status;

	@Column
	private BigDecimal costPerDay;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAdress() {
		return adress;
	}

	@Override
	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Override
	public Integer getWidth() {
		return width;
	}

	@Override
	public void setWidth(Integer width) {
		this.width = width;
	}

	@Override
	public Integer getLength() {
		return length;
	}

	@Override
	public void setLength(Integer length) {
		this.length = length;
	}

	@Override
	public ParkingType getStatus() {
		return status;
	}

	@Override
	public void setStatus(ParkingType status) {
		this.status = status;
	}

	@Override
	public BigDecimal getCostPerDay() {
		return costPerDay;
	}

	@Override
	public void setCostPerDay(BigDecimal costPerDay) {
		this.costPerDay = costPerDay;
	}
}
