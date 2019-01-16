package com.itacademy.jd2.vvm.parking.web.dto;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDTO {

	private Integer id;

	private Integer userAccountId;

	private BigDecimal value;

	private String description;

	private Date created;

	private Date updated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClientId() {
		return userAccountId;
	}

	public void setClientId(Integer clientId) {
		this.userAccountId = clientId;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
