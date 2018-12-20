package com.itacademy.jd2.vvm.parking.dao.orm.impl;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.IFotoDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Foto;

@Repository
public class FotoDaoImpl extends AbstractDaoImpl<IFoto, Integer> implements IFotoDao {

	protected FotoDaoImpl() {
		super(Foto.class);
	}

	@Override
	public IFoto createEntity() {
		final Foto foto = new Foto();
		return foto;
	}

}
