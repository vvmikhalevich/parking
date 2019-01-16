package com.itacademy.jd2.vvm.parking.dao.orm.impl.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.RoleType;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;

@Entity
public class UserAccount extends BaseEntity implements IUserAccount {

	@JoinTable(name = "place_2_user_account", joinColumns = {
			@JoinColumn(name = "user_account_id") }, inverseJoinColumns = { @JoinColumn(name = "place_id") })
	@ManyToMany(targetEntity = Place.class, fetch = FetchType.LAZY)
	private Set<IPlace> places = new HashSet<>();

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	@Enumerated(EnumType.ORDINAL)
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

	public Set<IPlace> getPlaces() {
		return places;
	}

	public void setPlaces(Set<IPlace> places) {
		this.places = places;
	}

}
