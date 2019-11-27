package com.itacademy.jd2.vvm.parking.service;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.filter.CarFilter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

	@Transactional
	void deleteAll();

}
