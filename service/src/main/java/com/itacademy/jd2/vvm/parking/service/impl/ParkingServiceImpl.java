package com.itacademy.jd2.vvm.parking.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vvm.parking.dao.api.IParkingDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IParking;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ParkingFilter;
import com.itacademy.jd2.vvm.parking.service.IParkingService;

@Service
public class ParkingServiceImpl implements IParkingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParkingServiceImpl.class);

	private IParkingDao dao;

	@Autowired
	public ParkingServiceImpl(IParkingDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IParking createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IParking entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new parking created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.debug("parking updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IParking get(final Integer id) {
		final IParking entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all parkings");
		dao.deleteAll();
	}

	@Override
	public List<IParking> getAll() {
		final List<IParking> all = dao.selectAll();
		return all;
	}

	@Override
	public List<IParking> find(ParkingFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(ParkingFilter filter) {
		return dao.getCount(filter);
	}

}
