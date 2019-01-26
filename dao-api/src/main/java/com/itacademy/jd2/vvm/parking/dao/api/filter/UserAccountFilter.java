package com.itacademy.jd2.vvm.parking.dao.api.filter;

public class UserAccountFilter extends AbstractFilter {
	
	private Integer userAccountId;
	
	private String userAccountLastName;

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

}
