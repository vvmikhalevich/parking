package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

import java.util.Date;

public interface ICar extends IBaseEntity {

	void setModel(IModel model);

	IModel getModel();

	void setNumber(String number);

	String getNumber();

	void setUserAccountId(Integer sold);

	Integer getUserAccountId();

	void setFotoId(Integer sold);

	Integer getFotoId();
}
