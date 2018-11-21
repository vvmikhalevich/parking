package com.itacademy.jd2.vvm.parking.jdbc.impl.entity;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;

public class Car extends BaseEntity implements ICar {

	private String number;

	private IModel model;

	private Integer userAccountId;

	private Integer fotoId;

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
	public Integer getUserAccountId() {
		return userAccountId;
	}

	@Override
	public void setUserAccountId(Integer user_account_id) {
		this.userAccountId = user_account_id;
	}

	@Override
	public Integer getFotoId() {
		return fotoId;
	}

	@Override
	public void setFotoId(Integer fotoId) {
		this.fotoId = fotoId;
	}

}
