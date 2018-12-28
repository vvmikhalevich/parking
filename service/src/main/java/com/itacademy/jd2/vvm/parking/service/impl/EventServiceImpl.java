package com.itacademy.jd2.vvm.parking.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vvm.parking.dao.api.IEventDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vvm.parking.dao.api.filter.EventFilter;
import com.itacademy.jd2.vvm.parking.service.IEventService;

@Service
public class EventServiceImpl implements IEventService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

	private IEventDao dao;

	@Autowired
	public EventServiceImpl(IEventDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IEvent createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IEvent entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new saved event: {}", entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public IEvent get(final Integer id) {
		final IEvent entity = dao.get(id);
		LOGGER.debug("entityById: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete entity: {}", id);
		dao.delete(id);
	}

	@Override
	public List<IEvent> getAll() {
		final List<IEvent> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<IEvent> find(final EventFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final EventFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all events");
		dao.deleteAll();
	}

}
