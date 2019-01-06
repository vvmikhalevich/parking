package com.itacademy.jd2.vvm.parking.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vvm.parking.dao.api.IPlaceOwnerDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlaceOwner;
import com.itacademy.jd2.vvm.parking.dao.api.filter.PlaceOwnerFilter;
import com.itacademy.jd2.vvm.parking.service.IPlaceOwnerService;

@Service
public class PlaceOwnerServiceImpl implements IPlaceOwnerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlaceOwnerServiceImpl.class);

	private IPlaceOwnerDao dao;

	@Autowired
	public PlaceOwnerServiceImpl(IPlaceOwnerDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IPlaceOwner createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IPlaceOwner entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			LOGGER.info("new saved PlaceOwner: {}", entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public IPlaceOwner get(final Integer id) {
		final IPlaceOwner entity = dao.get(id);
		LOGGER.debug("entityById: {}", entity);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		LOGGER.info("delete entity: {}", id);
		dao.delete(id);
	}

	@Override
	public List<IPlaceOwner> getAll() {
		final List<IPlaceOwner> all = dao.selectAll();
		LOGGER.debug("total count in DB: {}", all.size());
		return all;
	}

	@Override
	public List<IPlaceOwner> find(final PlaceOwnerFilter filter) {
		return dao.find(filter);
	}

	@Override
	public long getCount(final PlaceOwnerFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all placeOwners");
		dao.deleteAll();
	}

	@Override
	public IPlaceOwner getFullInfo(Integer id) {
		return dao.getFullInfo(id);
	}

}
