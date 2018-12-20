package com.itacademy.jd2.vvm.parking.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.ITariffDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;
import com.itacademy.jd2.vvm.parking.dao.api.filter.TariffFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Tariff;

@Repository
public class TariffDaoImpl extends AbstractDaoImpl<ITariff, Integer> implements ITariffDao {

	protected TariffDaoImpl() {
		super(Tariff.class);
	}

	@Override
	public ITariff createEntity() {
		final Tariff tariff = new Tariff();
		return tariff;
	}

	@Override
	public List<ITariff> find(TariffFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(TariffFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ITariff getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
