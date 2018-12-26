package com.itacademy.jd2.vvm.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.filter.PlaceFilter;

public interface IPlaceService {

	IPlace get(Integer id);

	List<IPlace> getAll();

	@Transactional
	void save(IPlace entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IPlace createEntity();

	List<IPlace> find(PlaceFilter filter);

	long getCount(PlaceFilter filter);

}
