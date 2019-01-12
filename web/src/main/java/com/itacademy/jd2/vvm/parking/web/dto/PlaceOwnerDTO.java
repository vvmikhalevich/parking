package com.itacademy.jd2.vvm.parking.web.dto;

import java.util.Date;

public class PlaceOwnerDTO {

	private Integer id;

	private Integer clientId;

	private Integer userAccountId;

	private String userAccountFirstName;

	private String userAccountLastName;

	private Integer placeId;

	private String placeName;

	private Date created;

	private Date updated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

}
