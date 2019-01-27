package com.itacademy.jd2.vvm.parking.dao.api.filter;

public class TransactionFilter extends AbstractFilter {

	private Integer userAccountId;

	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
	}

}
