package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.RoleType;

public interface IUserAccount extends IBaseEntity {

	void setFirstName(String name);

	String getFirstName();

	void setLastName(String name);

	String getLastName();

	void setRole(RoleType role);

	RoleType getRole();

	void setMail(String mail);

	String getMail();

	void setPassword(String password);

	String getPassword();

}
