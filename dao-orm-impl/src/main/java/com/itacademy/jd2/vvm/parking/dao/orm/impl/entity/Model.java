package com.itacademy.jd2.vvm.parking.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IBrand;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;

@Entity
public class Model extends BaseEntity implements IModel {
	@Column
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Brand.class)
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
