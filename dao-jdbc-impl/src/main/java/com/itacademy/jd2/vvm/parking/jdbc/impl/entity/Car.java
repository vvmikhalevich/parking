package com.itacademy.jd2.vvm.parking.jdbc.impl.entity;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;

public class Car extends BaseEntity implements ICar {

	private String number;

	private IModel model;

	private ITariff tariff;

	private IFoto foto;

	private IUserAccount userAccount;

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public void setModel(final IModel model) {
		this.model = model;
	}

	public ITariff getTariff() {
		return tariff;
	}

	public void setTariff(ITariff tariff) {
		this.tariff = tariff;
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
	public IFoto getFoto() {
		return foto;
	}

	@Override
	public void setFoto(IFoto foto) {
		this.foto = foto;
	}

	public IUserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
