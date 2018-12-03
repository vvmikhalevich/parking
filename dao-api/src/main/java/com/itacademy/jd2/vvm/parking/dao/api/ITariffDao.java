package com.itacademy.jd2.vvm.parking.dao.api;

import java.util.List;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;
import com.itacademy.jd2.vvm.parking.dao.api.filter.TariffFilter;

public interface ITariffDao extends IDao<ITariff, Integer> {

	List<ITariff> find(TariffFilter filter);

	long getCount(TariffFilter filter);

	ITariff getFullInfo(Integer id);

}
