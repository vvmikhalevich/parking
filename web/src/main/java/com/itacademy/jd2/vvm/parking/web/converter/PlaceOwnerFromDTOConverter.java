package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlaceOwner;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.service.IPlaceOwnerService;
import com.itacademy.jd2.vvm.parking.service.IPlaceService;
import com.itacademy.jd2.vvm.parking.service.IUserAccountService;
import com.itacademy.jd2.vvm.parking.web.dto.PlaceOwnerDTO;

@Component
public class PlaceOwnerFromDTOConverter implements Function<PlaceOwnerDTO, IPlaceOwner> {

	@Autowired
	private IPlaceOwnerService placeOwnerService;

	@Autowired
	private IPlaceService placeService;

	@Autowired
	private IUserAccountService userAccountService;

	@Override
	public IPlaceOwner apply(final PlaceOwnerDTO dto) {
		final IPlaceOwner entity = placeOwnerService.createEntity();
		entity.setId(dto.getId());

		final IPlace place = placeService.createEntity();
		place.setId(dto.getPlaceId());
		place.setName(dto.getPlaceName());
		entity.setPlace(place);

		final IUserAccount userAccount = userAccountService.createEntity();
		userAccount.setId(dto.getUserAccountId());
		entity.setUserAccount(userAccount);

		return entity;
	}
}
