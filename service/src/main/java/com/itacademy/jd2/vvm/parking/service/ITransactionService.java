package com.itacademy.jd2.vvm.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.vvm.parking.dao.api.filter.TransactionFilter;

public interface ITransactionService {

	ITransaction get(Integer id);

	List<ITransaction> getAll();

	@Transactional
	void save(ITransaction entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	ITransaction createEntity();

	List<ITransaction> find(TransactionFilter filter);

}
