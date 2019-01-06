package com.itacademy.jd2.vvm.parking.dao.orm.impl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;

@Entity
public class Event extends BaseEntity implements IEvent {

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Car.class)
	private ICar car;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Place.class)
	private IPlace place;

	@Column(updatable = false)
	private Date timeStart;

	@Column
	private Date timeEnd;

	@Override
	public ICar getCar() {
		return car;
	}

	@Override
	public void setCar(ICar car) {
		this.car = car;
	}

	@Override
	public IPlace getPlace() {
		return place;
	}

	@Override
	public void setPlace(IPlace place) {
		this.place = place;
	}

	@Override
	public Date getTimeStart() {
		return timeStart;
	}

	@Override
	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}

	@Override
	public Date getTimeEnd() {
		return timeEnd;
	}

	@Override
	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

}
