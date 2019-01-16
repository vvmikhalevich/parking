package com.itacademy.jd2.vvm.parking.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;

@Entity
public class Car extends BaseEntity implements ICar {

	@Column
	private String number;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Model.class)
	private IModel model;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
	private IUserAccount userAccount;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Foto.class)
	private IFoto foto;

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public void setModel(IModel model) {
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
	public IFoto getFoto() {
		return foto;
	}

	@Override
	public void setFoto(IFoto foto) {
		this.foto = foto;
	}

	@Override
	public IUserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
