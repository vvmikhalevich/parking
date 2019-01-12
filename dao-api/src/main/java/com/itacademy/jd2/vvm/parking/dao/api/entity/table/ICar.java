package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

public interface ICar extends IBaseEntity {

	IModel getModel();

	void setModel(IModel model);

	String getNumber();

	void setNumber(String number);

	IFoto getFoto();

	void setFoto(IFoto foto);

}
