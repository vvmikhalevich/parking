package com.itacademy.jd2.vvm.parking.web.dto;

import java.util.Date;

public class CarDTO {

	private Integer id;

	private Integer modelId;

	private String modelName;

	private String number;

	private Integer userAccountId;

	private String userAccountFirstName;

	private String userAccountLastName;

	private Integer fotoId;

	private String fotoName;

	private Date created;

	private Date updated;

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

	public Integer getFotoId() {
		return fotoId;
	}

	public void setFotoId(Integer fotoId) {
		this.fotoId = fotoId;
	}

	public String getFotoName() {
		return fotoName;
	}

	public void setFotoName(String fotoName) {
		this.fotoName = fotoName;
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
