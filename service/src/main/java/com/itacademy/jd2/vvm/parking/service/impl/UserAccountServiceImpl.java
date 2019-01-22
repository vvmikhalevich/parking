package com.itacademy.jd2.vvm.parking.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vvm.parking.dao.api.IUserAccountDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.vvm.parking.service.IUserAccountService;
import com.itacademy.jd2.vvm.parking.service.util.SendMailTLS;

@Service
public class UserAccountServiceImpl implements IUserAccountService {

	private IUserAccountDao dao;

	@Autowired
	public UserAccountServiceImpl(IUserAccountDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public IUserAccount createEntity() {

		return dao.createEntity();
	}

	@Override
	public void save(final IUserAccount entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			entity.setCreated(modifedOn);
			dao.insert(entity);
			String email = entity.getEmail();

			SendMailTLS.main(email);

		} else {
			dao.update(entity);
		}
	}

	@Override
	public IUserAccount get(final Integer id) {
		final IUserAccount entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		// remove all references
		final IUserAccount iUserAccount = dao.get(id);
		dao.update(iUserAccount);

		dao.delete(id);
	}

	@Override
	public void deleteAll() {

		dao.deleteAll();
	}

	@Override
	public List<IUserAccount> getAll() {
		final List<IUserAccount> all = dao.selectAll();
		return all;
	}

	@Override
	public long getCount(UserAccountFilter filter) {
		return dao.getCount(filter);
	}

	@Override
	public List<IUserAccount> find(UserAccountFilter filter) {
		return dao.find(filter);
	}

	@Override
	public IUserAccount findByLogin(String username) {

		return dao.findByLogin(username);
	}

}
