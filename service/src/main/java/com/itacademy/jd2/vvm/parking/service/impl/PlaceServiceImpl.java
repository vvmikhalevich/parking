package com.itacademy.jd2.vvm.parking.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vvm.parking.dao.api.IPlaceDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.filter.PlaceFilter;
import com.itacademy.jd2.vvm.parking.service.IPlaceService;

@Service
public class PlaceServiceImpl implements IPlaceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlaceServiceImpl.class);

	private IPlaceDao dao;

	@Autowired
	public PlaceServiceImpl(IPlaceDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IPlace createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IPlace entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new saved place: {}", entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public IPlace get(final Integer id) {
		final IPlace entity = dao.get(id);
		LOGGER.debug("entityById: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete entity: {}", id);
		dao.delete(id);
	}

	@Override
	public List<IPlace> getAll() {
		final List<IPlace> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<IPlace> find(final PlaceFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final PlaceFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all places");
		dao.deleteAll();
	}

}
