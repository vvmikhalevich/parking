package com.itacademy.jd2.vvm.parking.jdbc.impl.entity;

import java.util.Date;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;

public class Car extends BaseEntity implements ICar {

	private String vin;

	private IModel model;

	private Boolean sold;

	private Date soldDate;

	@Override
	public String getVin() {
		return vin;
	}

	@Override
	public void setVin(final String vin) {
		this.vin = vin;
	}

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public void setModel(final IModel model) {
		this.model = model;
	}

	@Override
	public Boolean getSold() {
		return sold;
	}

	@Override
	public void setSold(final Boolean sold) {
		this.sold = sold;
	}

	@Override
	public Date getSoldDate() {
		return soldDate;
	}

	@Override
	public void setSoldDate(final Date soldDate) {
		this.soldDate = soldDate;
	}

}
