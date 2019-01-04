package com.itacademy.jd2.vvm.parking.web.dto;

import java.util.Date;

import javax.validation.constraints.Size;

public class FotoDTO {

	private Integer id;
	@Size(min = 1, max = 20)
	private String link;

	private Date created;

	private Date updated;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(final String link) {
		this.link = link;
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
