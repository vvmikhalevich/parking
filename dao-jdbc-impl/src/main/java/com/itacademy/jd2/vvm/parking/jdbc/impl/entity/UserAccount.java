package com.itacademy.jd2.vvm.parking.jdbc.impl.entity;

import java.util.Set;

import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.RoleType;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;

public class UserAccount extends BaseEntity implements IUserAccount {

	private String firstName;

	private String lastName;

	private RoleType role;

	private String mail;

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
	public void setEmail(String mail) {
		this.mail = mail;

	}

	@Override
	public String getEmail() {

		return mail;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;

	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public Set<IPlace> getPlaces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPlaces(Set<IPlace> places) {
		// TODO Auto-generated method stub
		
	}

}
