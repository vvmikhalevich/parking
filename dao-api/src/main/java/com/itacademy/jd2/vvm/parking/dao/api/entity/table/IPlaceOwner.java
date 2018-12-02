package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

public interface IPlaceOwner extends IBaseEntity {

	IUserAccount getUserAccount();

	void setUserAccount(IUserAccount userAccount);

	IPlace getPlace();

	void setPlace(IPlace place);

}
