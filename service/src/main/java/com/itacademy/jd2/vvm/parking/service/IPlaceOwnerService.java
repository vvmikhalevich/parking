package com.itacademy.jd2.vvm.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlaceOwner;
import com.itacademy.jd2.vvm.parking.dao.api.filter.PlaceOwnerFilter;

public interface IPlaceOwnerService {

	IPlaceOwner get(Integer id);

	List<IPlaceOwner> getAll();

	@Transactional
	void save(IPlaceOwner entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IPlaceOwner createEntity();

	List<IPlaceOwner> find(PlaceOwnerFilter filter);

	long getCount(PlaceOwnerFilter filter);

	IPlaceOwner getFullInfo(Integer id);

}
