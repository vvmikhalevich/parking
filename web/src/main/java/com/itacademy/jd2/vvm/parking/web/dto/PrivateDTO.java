package com.itacademy.jd2.vvm.parking.web.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Size;

public class PrivateDTO {

	private Integer id;
	@Size(min = 1, max = 20)

	private Integer userAccountId;

	private String userAccountFirstName;

	private String userAccountLastName;

	private String role;

	// @Email
	private String email;

	private String password;

	private Date created;

	private Date updated;

	private Integer modelId;

	private String modelName;

	private String number;

	private Integer tariffId;

	private String tariffName;

	private BigDecimal tariffCostPerDay;

	private Integer placeId;

	private String placeName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
	}

	public String getUserAccountFirstName() {
		return userAccountFirstName;
	}

	public void setUserAccountFirstName(String userAccountFirstName) {
		this.userAccountFirstName = userAccountFirstName;
	}

	public String getUserAccountLastName() {
		return userAccountLastName;
	}

	public void setUserAccountLastName(String userAccountLastName) {
		this.userAccountLastName = userAccountLastName;
	}

	public Integer getTariffId() {
		return tariffId;
	}

	public void setTariffId(Integer tariffId) {
		this.tariffId = tariffId;
	}

	public String getTariffName() {
		return tariffName;
	}

	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}

	public Integer getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
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

	public BigDecimal getTariffCostPerDay() {
		return tariffCostPerDay;
	}

	public void setTariffCostPerDay(BigDecimal tariffCostPerDay) {
		this.tariffCostPerDay = tariffCostPerDay;
	}

	

}
