package com.itacademy.jd2.vvm.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IClient;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ClientFilter;

public interface IClientService {

	IClient get(Integer id);

	List<IClient> getAll();

	@Transactional
	void save(IClient entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IClient createEntity();

	List<IClient> find(ClientFilter filter);

	long getCount(ClientFilter filter);

}
