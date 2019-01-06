package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlaceOwner;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.web.dto.PlaceOwnerDTO;

@Component
public class PlaceOwnerToDTOConverter implements Function<IPlaceOwner, PlaceOwnerDTO> {

	@Override
	public PlaceOwnerDTO apply(final IPlaceOwner entity) {
		final PlaceOwnerDTO dto = new PlaceOwnerDTO();
		dto.setId(entity.getId());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		final IPlace place = entity.getPlace();
		if (place != null) {
			dto.setPlaceId(place.getId());
			dto.setPlaceName(place.getName());

		}

		final IUserAccount userAccount = entity.getUserAccount();
		if (userAccount != null) {
			dto.setUserAccountId(userAccount.getId());
			dto.setUserAccountFirstName(userAccount.getFirstName());
			dto.setUserAccountLastName(userAccount.getLastName());

		}

		return dto;
	}

}
