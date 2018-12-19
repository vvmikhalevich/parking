package com.itacademy.jd2.vvm.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IBrand;
import com.itacademy.jd2.vvm.parking.dao.api.filter.BrandFilter;

public interface IBrandService {

	IBrand get(Integer id);

	List<IBrand> getAll();

	@Transactional
	void save(IBrand entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IBrand createEntity();

	List<IBrand> find(BrandFilter filter);

	long getCount(BrandFilter filter);

	void save(IBrand... entity);

}
