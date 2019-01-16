package com.itacademy.jd2.vvm.parking.dao.orm.impl.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

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
	private String status;

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
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
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

	public BigDecimal getCostPerDay() {
		return costPerDay;
	}

	public void setCostPerDay(BigDecimal costPerDay) {
		this.costPerDay = costPerDay;
	}
}
