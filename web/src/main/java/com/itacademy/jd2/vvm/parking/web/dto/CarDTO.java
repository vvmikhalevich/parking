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

	private Integer tariffId;

	private String tariffName;

	private Integer fotoId;

	private String link;

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

	public Integer getFotoId() {
		return fotoId;
	}

	public void setFotoId(Integer fotoId) {
		this.fotoId = fotoId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

}
