package com.itacademy.jd2.vvm.parking.service;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IParking;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ParkingFilter;

import java.util.List;
import javax.transaction.Transactional;

public interface IParkingService {

	IParking get(Integer id);

	List<IParking> getAll();

	@Transactional
	void save(IParking entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IParking createEntity();

	List<IParking> find(ParkingFilter filter);

	long getCount(ParkingFilter filter);

}
