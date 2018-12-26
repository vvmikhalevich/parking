package com.itacademy.jd2.vvm.parking.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vvm.parking.dao.api.IFotoDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;
import com.itacademy.jd2.vvm.parking.dao.api.filter.FotoFilter;
import com.itacademy.jd2.vvm.parking.service.IFotoService;

@Service
public class FotoServiceImpl implements IFotoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FotoServiceImpl.class);

	private IFotoDao dao;

	@Autowired
	public FotoServiceImpl(IFotoDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IFoto createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IFoto entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new foto created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.debug("foto updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public IFoto get(final Integer id) {
		final IFoto entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all fotos");
		dao.deleteAll();
	}

	@Override
	public List<IFoto> getAll() {
		final List<IFoto> all = dao.selectAll();
		return all;
	}

	@Override
	public List<IFoto> find(FotoFilter filter) {
		return dao.find(filter);
	}

}
