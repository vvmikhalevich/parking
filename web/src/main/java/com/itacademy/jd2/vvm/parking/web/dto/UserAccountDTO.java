package com.itacademy.jd2.vvm.parking.web.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.RoleType;

public class UserAccountDTO {

	private Integer id;
	@Size(min = 1, max = 20)
	private String firstName;

	private String lastName;

	private RoleType role;

	private String email;

	private String password;

	private Date created;

	private Date updated;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
