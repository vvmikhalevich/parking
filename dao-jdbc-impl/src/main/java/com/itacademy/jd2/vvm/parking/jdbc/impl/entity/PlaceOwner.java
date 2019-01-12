package com.itacademy.jd2.vvm.parking.jdbc.impl.entity;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IClient;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlaceOwner;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;

public class PlaceOwner extends BaseEntity implements IPlaceOwner {

	private IClient client;

	private IPlace place;

	private String status;

	public IPlace getPlace() {
		return place;
	}

	public void setPlace(IPlace place) {
		this.place = place;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public IClient getClient() {
		return client;
	}

	public void setClient(IClient client) {
		this.client = client;
	}

	@Override
	public IUserAccount getUserAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserAccount(IUserAccount userAccount) {
		// TODO Auto-generated method stub
		
	}

}
