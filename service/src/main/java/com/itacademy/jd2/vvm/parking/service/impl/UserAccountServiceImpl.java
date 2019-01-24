package com.itacademy.jd2.vvm.parking.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vvm.parking.dao.api.IUserAccountDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.vvm.parking.service.IUserAccountService;
import com.itacademy.jd2.vvm.parking.service.util.SendMailTLS;
import com.itacademy.jd2.vvm.parking.service.util.SaltedMD5Example;;

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
			String password = entity.getPassword();
			String hash = null;
			try {
				hash = SaltedMD5Example.main(password);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String email = entity.getEmail();

			entity.setPassword(hash);

			dao.insert(entity);

			// SendMailTLS.main(email);

		} else {
			String password = entity.getPassword();
			String hash = null;
			try {
				hash = SaltedMD5Example.main(password);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			entity.setPassword(hash);

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
