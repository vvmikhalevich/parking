package com.itacademy.jd2.vvm.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;
import com.itacademy.jd2.vvm.parking.dao.api.filter.FotoFilter;

public interface IFotoService {

	IFoto get(Integer id);

	List<IFoto> getAll();

	@Transactional
	void save(IFoto entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IFoto createEntity();

	List<IFoto> find(FotoFilter filter);

}
