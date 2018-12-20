package com.itacademy.jd2.vvm.parking.dao.orm.impl.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IClient;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;

@Entity
public class Client extends BaseEntity implements IClient {

	@OneToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
	private IUserAccount userAccount;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Car.class)
	private ICar car;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Tariff.class)
	private ITariff tariff;

	@Override
	public IUserAccount getUserAccount() {
		return userAccount;
	}

	@Override
	public void setUserAccount(IUserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public ICar getCar() {
		return car;
	}

	@Override
	public void setCar(ICar car) {
		this.car = car;
	}

	@Override
	public ITariff getTariff() {
		return tariff;
	}

	@Override
	public void setTariff(ITariff tariff) {
		this.tariff = tariff;
	}

}
