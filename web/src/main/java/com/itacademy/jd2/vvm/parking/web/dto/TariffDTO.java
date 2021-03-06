package com.itacademy.jd2.vvm.parking.web.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Size;

public class TariffDTO {

	private Integer id;
	@Size(min = 1, max = 20)
	private String name;

	private BigDecimal costPerDay;

	private String status;

	private Date created;

	private Date updated;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public BigDecimal getCostPerDay() {
		return costPerDay;
	}

	public void setCostPerDay(BigDecimal costPerDay) {
		this.costPerDay = costPerDay;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(final Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(final Date updated) {
		this.updated = updated;
	}

}
