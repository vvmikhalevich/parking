package com.itacademy.jd2.vvm.parking.dao.orm.impl.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlaceOwner;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;

@Entity
public class PlaceOwner extends BaseEntity implements IPlaceOwner {

	@OneToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
	private IUserAccount userAccount;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Place.class)
	private IPlace place;

	@Override
	public IUserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public IPlace getPlace() {
		return place;
	}

	@Override
	public void setPlace(IPlace place) {
		this.place = place;
	}

}
