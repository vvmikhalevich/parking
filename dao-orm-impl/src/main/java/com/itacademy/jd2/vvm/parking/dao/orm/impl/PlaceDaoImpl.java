package com.itacademy.jd2.vvm.parking.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.IPlaceDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.filter.PlaceFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Place;

@Repository
public class PlaceDaoImpl extends AbstractDaoImpl<IPlace, Integer> implements IPlaceDao {

	protected PlaceDaoImpl() {
		super(Place.class);
	}

	@Override
	public IPlace createEntity() {
		Place place = new Place();
		return place;
	}

	@Override
	public List<IPlace> find(PlaceFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(PlaceFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IPlace getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
