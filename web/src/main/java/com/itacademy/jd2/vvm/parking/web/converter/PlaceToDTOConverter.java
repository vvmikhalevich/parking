package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IParking;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.web.dto.PlaceDTO;

@Component
public class PlaceToDTOConverter implements Function<IPlace, PlaceDTO> {

	@Override
	public PlaceDTO apply(final IPlace entity) {
		final PlaceDTO dto = new PlaceDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		final IParking parking = entity.getParking();
		if (parking != null) {
			dto.setParkingId(parking.getId());
			dto.setParkingName(parking.getName());

		}

		final ICar car = entity.getCar();
		if (car != null) {
			dto.setCarId(car.getId());
			dto.setCarNumber(car.getNumber());
		} else {
			dto.setCarId(0);
		}

		final IUserAccount userAccount = entity.getUserAccount();
		if (userAccount != null) {
			dto.setUserAccountId(userAccount.getId());
			dto.setUserAccountLastName(userAccount.getLastName());

		} else {
			dto.setUserAccountId(0);
		}

		return dto;
	}

}
