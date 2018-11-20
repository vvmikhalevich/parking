package com.itacademy.jd2.vvm.parking.dao.api;

import java.util.List;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.filter.CarFilter;

public interface ICarDao extends IDao<ICar, Integer> {

	List<ICar> find(CarFilter filter);

	long getCount(CarFilter filter);

	ICar getFullInfo(Integer id);

}
