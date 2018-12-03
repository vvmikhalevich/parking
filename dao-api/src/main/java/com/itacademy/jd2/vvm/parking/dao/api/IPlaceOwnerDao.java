package com.itacademy.jd2.vvm.parking.dao.api;

import java.util.List;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlaceOwner;
import com.itacademy.jd2.vvm.parking.dao.api.filter.PlaceOwnerFilter;

public interface IPlaceOwnerDao extends IDao<IPlaceOwner, Integer> {

	List<IPlaceOwner> find(PlaceOwnerFilter filter);

	long getCount(PlaceOwnerFilter filter);

	IPlaceOwner getFullInfo(Integer id);

}
