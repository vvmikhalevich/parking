package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

public interface ICar extends IBaseEntity {

	void setModel(IModel model);

	IModel getModel();

	void setNumber(String number);

	String getNumber();

	void setUserAccount(IUserAccount userAccount);

	IUserAccount getUserAccount();

	void setFotoId(Integer id);

	Integer getFotoId();

}
