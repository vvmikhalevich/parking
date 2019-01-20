package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.RoleType;

public interface IUserAccount extends IBaseEntity {

	String getFirstName();

	void setFirstName(String name);

	String getLastName();

	void setLastName(String name);

	RoleType getRole();

	void setRole(RoleType role);

	String getEmail();

	void setEmail(String email);

	String getPassword();

	void setPassword(String password);

}
