package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.service.ICarService;
import com.itacademy.jd2.vvm.parking.service.IPlaceService;
import com.itacademy.jd2.vvm.parking.service.IUserAccountService;
import com.itacademy.jd2.vvm.parking.web.dto.CarDTO;

@Component
public class CarPutFromDTOConverter implements Function<CarDTO, IPlace> {

	@Autowired
	private ICarService carService;

	@Autowired
	private IPlaceService placeService;

	@Autowired
	private IUserAccountService userAccountService;

	@Override
	public IPlace apply(final CarDTO dto) {

		final IPlace entity = placeService.createEntity();

		entity.setId(dto.getPlaceId());

		final ICar car = carService.createEntity();
		car.setId(dto.getId());

		entity.setCar(car);

		final IUserAccount userAccount = userAccountService.createEntity();
		userAccount.setId(dto.getUserAccountId());

		entity.setUserAccount(userAccount);

		return entity;
	}
}
