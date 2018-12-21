package com.itacademy.jd2.vvm.parking.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.IPlaceOwnerDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlaceOwner;
import com.itacademy.jd2.vvm.parking.dao.api.filter.PlaceOwnerFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.PlaceOwner;

@Repository
public class PlaceOwnerDaoImpl extends AbstractDaoImpl<IPlaceOwner, Integer> implements IPlaceOwnerDao {

	protected PlaceOwnerDaoImpl() {
		super(PlaceOwner.class);
	}

	@Override
	public IPlaceOwner createEntity() {
		PlaceOwner placeOwner = new PlaceOwner();
		return placeOwner;
	}

	@Override
	public List<IPlaceOwner> find(PlaceOwnerFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(PlaceOwnerFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IPlaceOwner getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
