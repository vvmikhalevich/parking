package com.itacademy.jd2.vvm.parking.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vvm.parking.service.impl.BrandServiceImpl;
import com.itacademy.jd2.vvm.parking.dao.api.IBrandDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IBrand;
import com.itacademy.jd2.vvm.parking.service.IBrandService;

@Service
public class BrandServiceImpl implements IBrandService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BrandServiceImpl.class);

	private IBrandDao dao;

	@Autowired
	public BrandServiceImpl(IBrandDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IBrand createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IBrand entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new brand created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.debug("brand updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IBrand get(final Integer id) {
		final IBrand entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all brands");
		dao.deleteAll();
	}

	@Override
	public List<IBrand> getAll() {
		final List<IBrand> all = dao.selectAll();
		return all;
	}

}
