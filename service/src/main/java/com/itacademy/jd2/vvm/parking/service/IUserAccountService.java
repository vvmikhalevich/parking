package com.itacademy.jd2.vvm.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ModelFilter;
import com.itacademy.jd2.vvm.parking.dao.api.filter.UserAccountFilter;

public interface IUserAccountService {

	IUserAccount get(Integer id);

	List<IUserAccount> getAll();

	@Transactional
	void save(IUserAccount entity);

	@Transactional
	void delete(Integer id);

	@Transactional
	void deleteAll();

	IUserAccount createEntity();

	long getCount(UserAccountFilter filter);

	List<IUserAccount> find(UserAccountFilter filter);

}
