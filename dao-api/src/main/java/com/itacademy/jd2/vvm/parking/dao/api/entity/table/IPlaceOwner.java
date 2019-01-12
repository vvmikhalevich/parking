package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

public interface IPlaceOwner extends IBaseEntity {

	IClient getClient();

	void setClient(IClient client);

	IPlace getPlace();

	void setPlace(IPlace place);

}
