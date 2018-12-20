package com.itacademy.jd2.vvm.parking.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.IEventDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vvm.parking.dao.api.filter.EventFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Event;

@Repository
public class EventDaoImpl extends AbstractDaoImpl<IEvent, Integer> implements IEventDao {

	protected EventDaoImpl() {
		super(Event.class);
	}

	@Override
	public IEvent createEntity() {
		Event event = new Event();
		return event;
	}

	@Override
	public List<IEvent> find(EventFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(EventFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IEvent getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
