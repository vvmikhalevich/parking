package com.itacademy.jd2.vvm.parking.dao.api;

import java.util.List;

import com.itacademy.jd2.vvm.parking.dao.api.filter.BrandFilter;
import com.itacademy.jd2.vvm.parking.dao.api.IDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IBrand;

public interface IBrandDao extends IDao<IBrand, Integer> {
	void save(IBrand... entities); // skip

	List<IBrand> find(BrandFilter filter); // skip

	long getCount(BrandFilter filter); // skip
}
