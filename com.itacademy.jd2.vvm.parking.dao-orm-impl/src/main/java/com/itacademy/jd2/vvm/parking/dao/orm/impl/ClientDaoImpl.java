package com.itacademy.jd2.vvm.parking.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.IClientDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IClient;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ClientFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Client;

@Repository
public class ClientDaoImpl extends AbstractDaoImpl<IClient, Integer> implements IClientDao {

	protected ClientDaoImpl() {
		super(Client.class);
	}

	@Override
	public IClient createEntity() {
		Client client = new Client();
		return client;
	}

	@Override
	public List<IClient> find(ClientFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(ClientFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IClient getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
