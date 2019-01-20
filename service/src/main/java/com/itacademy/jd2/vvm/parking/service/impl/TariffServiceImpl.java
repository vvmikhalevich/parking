package com.itacademy.jd2.vvm.parking.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vvm.parking.dao.api.ITariffDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;
import com.itacademy.jd2.vvm.parking.dao.api.filter.TariffFilter;
import com.itacademy.jd2.vvm.parking.service.ITariffService;

@Service
public class TariffServiceImpl implements ITariffService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TariffServiceImpl.class);

	private ITariffDao dao;

	@Autowired
	public TariffServiceImpl(ITariffDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public ITariff createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ITariff entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new tariff created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.debug("tariff updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public ITariff get(final Integer id) {
		final ITariff entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all tariffs");
		dao.deleteAll();
	}

	@Override
	public List<ITariff> getAll() {
		final List<ITariff> all = dao.selectAll();
		return all;
	}

	@Override
	public List<ITariff> find(TariffFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(TariffFilter filter) {
		return dao.getCount(filter);
	}

}
