package com.itacademy.jd2.vvm.parking.dao.api.filter;

public class CarFilter extends AbstractFilter {

	private Integer loggedUserId;

	private Integer modelId;

	private String number;

	private Integer userAccountId;

	private Integer fotoId;

	public Integer getLoggedUserId() {
		return loggedUserId;
	}

	public void setLoggedUserId(Integer loggedUserId) {
		this.loggedUserId = loggedUserId;
	}

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
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

	public Integer getFotoId() {
		return fotoId;
	}

	public void setFotoId(Integer fotoId) {
		this.fotoId = fotoId;
	}

}
