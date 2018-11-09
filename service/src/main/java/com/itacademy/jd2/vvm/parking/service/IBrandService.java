package com.itacademy.jd2.vvm.parking.service;

import java.util.List;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IBrand;
import com.itacademy.jd2.vvm.parking.dao.api.filter.BrandFilter;

public interface IBrandService {

	IBrand get(Integer id);

	List<IBrand> getAll();

	void save(IBrand entity);

	void delete(Integer id);

	void deleteAll();

	IBrand createEntity();

}
