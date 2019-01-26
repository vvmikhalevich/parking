package com.itacademy.jd2.vvm.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vvm.parking.dao.api.filter.EventFilter;

public interface IEventService {

	IEvent get(Integer id);

	List<IEvent> getAll();

	@Transactional
	void save(IEvent entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IEvent createEntity();

	List<IEvent> find(EventFilter filter);

	long getCount(EventFilter filter);

	IEvent getFullInfo(Integer id);

	IEvent findByPlace(Integer id);

}
