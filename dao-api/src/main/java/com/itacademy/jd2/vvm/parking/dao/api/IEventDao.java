package com.itacademy.jd2.vvm.parking.dao.api;

import java.util.List;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vvm.parking.dao.api.filter.EventFilter;

public interface IEventDao extends IDao<IEvent, Integer> {

	List<IEvent> find(EventFilter filter);

	long getCount(EventFilter filter);

	IEvent getFullInfo(Integer id);

}
