package com.itacademy.jd2.vvm.parking.jdbc.impl.entity;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IBrand;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;

public class Model extends BaseEntity implements IModel {

	private String name;

	private IBrand brand;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public IBrand getBrand() {
		return brand;
	}

	@Override
	public void setBrand(final IBrand brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Model [name=" + name + ", getId()=" + getId() + "]";
	}
}
