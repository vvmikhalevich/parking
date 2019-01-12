package com.itacademy.jd2.vvm.parking.dao.orm.impl.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IClient;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlaceOwner;

@Entity
public class PlaceOwner extends BaseEntity implements IPlaceOwner {

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Client.class)
	private IClient client;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Place.class)
	private IPlace place;

	@Override
	public IPlace getPlace() {
		return place;
	}

	@Override
	public void setPlace(IPlace place) {
		this.place = place;
	}

	public IClient getClient() {
		return client;
	}

	public void setClient(IClient client) {
		this.client = client;
	}

}
