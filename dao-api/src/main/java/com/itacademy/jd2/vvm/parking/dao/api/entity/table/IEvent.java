package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

import java.util.Date;

public interface IEvent extends IBaseEntity {

	ICar getCar();

	void setCar(ICar car);

	IPlace getPlace();

	void setPlace(IPlace place);

	Date getTimeStart();

	void setTimeStart(Date timeStart);

	Date getTimeEnd();

	void setTimeEnd(Date timeEnd);
}
