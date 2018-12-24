package com.itacademy.jd2.vvm.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;
import com.itacademy.jd2.vvm.parking.dao.api.filter.TariffFilter;

public interface ITariffService {

	ITariff get(Integer id);

	List<ITariff> getAll();

	@Transactional
	void save(ITariff entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	ITariff createEntity();

	List<ITariff> find(TariffFilter filter);

}
