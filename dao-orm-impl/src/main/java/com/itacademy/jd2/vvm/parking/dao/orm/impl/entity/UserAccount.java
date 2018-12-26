package com.itacademy.jd2.vvm.parking.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.RoleType;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;

@Entity
public class UserAccount extends BaseEntity implements IUserAccount {

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private RoleType role;

	@Column
	private String email;

	@Column
	private String password;

	@Override
	public void setFirstName(String name) {
		this.firstName = name;

	}

	@Override
	public String getFirstName() {

		return firstName;
	}

	@Override
	public void setLastName(String name) {
		this.lastName = name;

	}

	@Override
	public String getLastName() {

		return lastName;
	}

	@Override
	public void setRole(RoleType role) {
		this.role = role;

	}

	@Override
	public RoleType getRole() {

		return role;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;

	}

	@Override
	public String getEmail() {

		return email;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;

	}

	@Override
	public String getPassword() {

		return password;
	}

}
