package com.itacademy.jd2.vvm.parking.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.IUserAccountDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.UserAccount;

@Repository
public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {

	protected UserAccountDaoImpl() {
        super(UserAccount.class);
    }
	
	@Override
	public IUserAccount createEntity() {
		final UserAccount userAccount = new UserAccount();
		return userAccount;
	}

	@Override
	public List<IUserAccount> find(UserAccountFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(UserAccountFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
