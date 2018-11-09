package com.itacademy.jd2.vvm.parking.service;

import java.util.List;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModelInfo;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ModelFilter;

public interface IModelService {

	IModel get(Integer id);

	List<IModel> getAll();

	void save(IModel entity);

	void delete(Integer id);

	void deleteAll();

	IModel createEntity();

	// IModelInfo createInfoEntity();

	// IModel getFullInfo(final Integer id);

	void save(IModel entity, IModelInfo infoEntity);

	long getCount(ModelFilter filter);

	List<IModel> find(ModelFilter filter);
}
