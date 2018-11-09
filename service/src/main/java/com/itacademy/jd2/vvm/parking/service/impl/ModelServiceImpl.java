package com.itacademy.jd2.vvm.parking.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vvm.parking.dao.api.IModelDao;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModelInfo;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ModelFilter;
import com.itacademy.jd2.vvm.parking.service.IModelService;

@Service
public class ModelServiceImpl implements IModelService {

	private IModelDao dao;

	@Autowired
	public ModelServiceImpl(IModelDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IModel createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final IModel entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			dao.update(entity);
		}
	}

	@Override
	public IModel get(final Integer id) {
		final IModel entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		// remove all references
		final IModel iModel = dao.get(id);
		dao.update(iModel);

		dao.delete(id);
	}

	@Override
	public void deleteAll() {

		dao.deleteAll();
	}

	@Override
	public List<IModel> getAll() {
		final List<IModel> all = dao.selectAll();
		return all;
	}

	@Override
	public void save(final IModel model, final IModelInfo info) {
		final Date modifiedDate = new Date();
		model.setUpdated(modifiedDate);
		info.setUpdated(modifiedDate);

		if (model.getId() == null) {
			model.setCreated(modifiedDate);
			dao.insert(model);

			info.setId(model.getId());
			info.setCreated(modifiedDate);
			info.setModel(model);

			model.setModelInfo(info);
		} else {
			dao.update(model);

		}
	}

	@Override
	public long getCount(ModelFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public List<IModel> find(ModelFilter filter) {
		return dao.find(filter);
	}

}
