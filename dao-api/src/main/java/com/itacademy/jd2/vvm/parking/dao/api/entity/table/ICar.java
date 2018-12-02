package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

public interface ICar extends IBaseEntity {

	IModel getModel();

	void setModel(IModel model);

	String getNumber();

	void setNumber(String number);

	IUserAccount getUserAccount();

	void setUserAccount(IUserAccount userAccount);

	IFoto getFoto();

	void setFoto(IFoto foto);

}
