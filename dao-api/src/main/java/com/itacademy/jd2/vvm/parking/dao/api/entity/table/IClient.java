package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

public interface IClient extends IBaseEntity {

	IUserAccount getUserAccount();

	void setUserAccount(IUserAccount userAccount);

	ICar getCar();

	void setCar(ICar car);

	ITariff getTariff();

	void setTariff(ITariff tariff);

}
