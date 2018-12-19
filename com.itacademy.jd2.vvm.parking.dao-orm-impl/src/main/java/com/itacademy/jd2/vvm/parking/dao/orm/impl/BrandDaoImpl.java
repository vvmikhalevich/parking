package com.itacademy.jd2.vvm.parking.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.IBrandDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IBrand;
import com.itacademy.jd2.vvm.parking.dao.api.filter.BrandFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Brand;

@Repository
public class BrandDaoImpl extends AbstractDaoImpl<IBrand, Integer> implements IBrandDao {

	protected BrandDaoImpl() {
		super(Brand.class);
	}

	@Override
	public IBrand createEntity() {
		final Brand brand = new Brand();
		return brand;
	}

	@Override
	public void save(IBrand... entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IBrand> find(BrandFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(BrandFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

}
