package com.itacademy.jd2.vvm.parking.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.ICarDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.filter.CarFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Car;

@Repository
public class CarDaoImpl extends AbstractDaoImpl<ICar, Integer> implements ICarDao {

    protected CarDaoImpl() {
        super(Car.class);
    }

    @Override
    public ICar createEntity() {
        Car car = new Car();
        return car;
    }

	@Override
	public List<ICar> find(CarFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(CarFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ICar getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

    

}
