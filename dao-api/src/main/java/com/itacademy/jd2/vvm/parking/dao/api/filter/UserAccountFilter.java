package com.itacademy.jd2.vvm.parking.dao.api.filter;

public class UserAccountFilter extends AbstractFilter {

	private Integer userAccountId;

	private String userAccountLastName;

	private String userEmail;

	private Integer loggedUserId;

	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
	}

	public String getUserAccountLastName() {
		return userAccountLastName;
	}

	public void setUserAccountLastName(String userAccountLastName) {
		this.userAccountLastName = userAccountLastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getLoggedUserId() {
		return loggedUserId;
	}

	public void setLoggedUserId(Integer loggedUserId) {
		this.loggedUserId = loggedUserId;
	}

}
