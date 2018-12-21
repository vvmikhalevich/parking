package com.itacademy.jd2.vvm.parking.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;

@Entity
public class Foto extends BaseEntity implements IFoto {

	@Column
	private String link;

	@Override
	public String getLink() {
		return link;
	}

	@Override
	public void setLink(String link) {
		this.link = link;
	}

}
