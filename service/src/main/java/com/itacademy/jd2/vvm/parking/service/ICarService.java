package com.itacademy.jd2.vvm.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.filter.CarFilter;

public interface ICarService {

	ICar get(Integer id);

	@Transactional
	void save(ICar entity);

	@Transactional
	void delete(Integer id);

	ICar createEntity();

	List<ICar> getAll();

	List<ICar> find(CarFilter filter);

	long getCount(CarFilter filter);

	ICar getFullInfo(Integer id);

}
