package com.itacademy.jd2.vvm.parking.dao.api.filter;

public class PlaceFilter extends AbstractFilter {
	private Integer parkingId;

	private Integer userAccountId;

	private Boolean withoutCar;

	private String number;

	public Integer getParkingId() {
		return parkingId;
	}

	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}

	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
	}

	public Boolean getWithoutCar() {
		return withoutCar;
	}

	public void setWithoutCar(Boolean withoutCar) {
		this.withoutCar = withoutCar;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}
