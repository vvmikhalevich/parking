package com.itacademy.jd2.vvm.parking.jdbc.impl.entity;

import java.util.HashSet;
import java.util.Set;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IBrand;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModelInfo;

public class Model extends BaseEntity implements IModel {

	private String name;

	private IBrand brand;

	private IModelInfo modelInfo;

	public IModelInfo getModelInfo() {
		return modelInfo;
	}

	public void setModelInfo(IModelInfo modelInfo) {
		this.modelInfo = modelInfo;
	}

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
