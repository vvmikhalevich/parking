package com.itacademy.jd2.vvm.parking.dao.api;

import java.util.List;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.filter.PlaceFilter;

public interface IPlaceDao extends IDao<IPlace, Integer> {

	List<IPlace> find(PlaceFilter filter);

	long getCount(PlaceFilter filter);

	IPlace getFullInfo(Integer id);

}
