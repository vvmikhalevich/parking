package com.itacademy.jd2.vvm.parking.dao.api;

import java.util.List;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IParking;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ParkingFilter;

public interface IParkingDao extends IDao<IParking, Integer> {

	List<IParking> find(ParkingFilter filter);

	long getCount(ParkingFilter filter);

	IParking getFullInfo(Integer id);

}
