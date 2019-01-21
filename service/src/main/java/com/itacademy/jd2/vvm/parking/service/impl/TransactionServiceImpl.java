package com.itacademy.jd2.vvm.parking.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vvm.parking.dao.api.ITransactionDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.vvm.parking.dao.api.filter.TransactionFilter;
import com.itacademy.jd2.vvm.parking.service.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

	private ITransactionDao dao;

	@Autowired
	public TransactionServiceImpl(ITransactionDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public ITransaction createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ITransaction entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new transaction created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.debug("transaction updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public ITransaction get(final Integer id) {
		final ITransaction entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all transactions");
		dao.deleteAll();
	}

	@Override
	public List<ITransaction> getAll() {
		final List<ITransaction> all = dao.selectAll();
		return all;
	}

	@Override
	public List<ITransaction> find(TransactionFilter filter) {
		return dao.find(filter);
	}

	@Override
	public ITransaction getFullInfo(Integer id) {
		final ITransaction entity = dao.getFullInfo(id);
		return entity;
	}

	@Override
	public long getCount(TransactionFilter filter) {
		return dao.getCount(filter);
	}

}
