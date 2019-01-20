package com.itacademy.jd2.vvm.parking.jdbc.impl.entity;

import java.math.BigDecimal;

import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.ParkingType;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IParking;

public class Parking extends BaseEntity implements IParking {

	private String name;

	private String adress;

	private Integer width;

	private Integer length;

	private ParkingType status;

	private BigDecimal costPerDay;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public ParkingType getStatus() {
		return status;
	}

	public void setStatus(ParkingType status) {
		this.status = status;
	}

	public BigDecimal getCostPerDay() {
		return costPerDay;
	}

	public void setCostPerDay(BigDecimal costPerDay) {
		this.costPerDay = costPerDay;
	}

}
