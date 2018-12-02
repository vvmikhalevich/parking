package com.itacademy.jd2.vvm.parking.jdbc.impl.entity;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;

public class Car extends BaseEntity implements ICar {

	private String number;

	private IModel model;

	private IUserAccount userAccount;

	private IFoto foto;

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public void setModel(final IModel model) {
		this.model = model;
	}

	@Override
	public String getNumber() {
		return number;
	}

	@Override
	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public IUserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public IFoto getFoto() {
		return foto;
	}

	@Override
	public void setFoto(IFoto foto) {
		this.foto = foto;
	}

}
