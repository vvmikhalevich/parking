package com.itacademy.jd2.vvm.parking.dao.api;

import java.util.List;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;
import com.itacademy.jd2.vvm.parking.dao.api.filter.FotoFilter;

public interface IFotoDao extends IDao<IFoto, Integer> {

	List<IFoto> find(FotoFilter filter);

	long getCount(FotoFilter filter);

}
