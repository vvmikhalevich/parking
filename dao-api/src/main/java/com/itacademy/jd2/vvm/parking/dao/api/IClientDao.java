package com.itacademy.jd2.vvm.parking.dao.api;

import java.util.List;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IClient;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ClientFilter;

public interface IClientDao extends IDao<IClient, Integer> {

	List<IClient> find(ClientFilter filter);

	long getCount(ClientFilter filter);

	IClient getFullInfo(Integer id);

}
