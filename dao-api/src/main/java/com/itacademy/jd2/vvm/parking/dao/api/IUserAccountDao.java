package com.itacademy.jd2.vvm.parking.dao.api;

import java.util.List;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.dao.api.filter.UserAccountFilter;

public interface IUserAccountDao extends IDao<IUserAccount, Integer> {

	List<IUserAccount> find(UserAccountFilter filter);

	long getCount(UserAccountFilter filter);

	// IUserAccount getFullInfo(final Integer id);
}
