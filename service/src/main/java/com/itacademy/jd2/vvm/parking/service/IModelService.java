package com.itacademy.jd2.vvm.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ModelFilter;

public interface IModelService {

	IModel get(Integer id);

	List<IModel> getAll();

	@Transactional
	void save(IModel entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IModel createEntity();

	// IModelInfo createInfoEntity();

	IModel getFullInfo(final Integer id);

	long getCount(ModelFilter filter);

	List<IModel> find(ModelFilter filter);

}
