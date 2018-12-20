package com.itacademy.jd2.vvm.parking.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.IParkingDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IParking;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ParkingFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Parking;

@Repository
public class ParkingDaoImpl extends AbstractDaoImpl<IParking, Integer> implements IParkingDao {

	protected ParkingDaoImpl() {
		super(Parking.class);
	}

	@Override
	public IParking createEntity() {
		final Parking parking = new Parking();
		return parking;
	}

	@Override
	public List<IParking> find(ParkingFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(ParkingFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IParking getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
