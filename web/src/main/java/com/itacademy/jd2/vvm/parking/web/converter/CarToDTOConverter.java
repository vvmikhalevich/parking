package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.web.dto.CarDTO;

@Component
public class CarToDTOConverter implements Function<ICar, CarDTO> {

	@Override
	public CarDTO apply(final ICar entity) {
		final CarDTO carDto = new CarDTO();
		carDto.setId(entity.getId());
		carDto.setNumber(entity.getNumber());
		carDto.setCreated(entity.getCreated());
		carDto.setUpdated(entity.getUpdated());
		final IModel model = entity.getModel();
		if (model != null) {
			carDto.setModelId(model.getId());
			carDto.setModelName(model.getName());

		}

		final IUserAccount userAccount = entity.getUserAccount();
		if (userAccount != null) {
			carDto.setUserAccountId(userAccount.getId());
			carDto.setUserAccountFirstName(userAccount.getFirstName());
			carDto.setUserAccountLastName(userAccount.getLastName());

		}
		final IFoto foto = entity.getFoto();

		if (foto != null) {
			carDto.setFotoId(foto.getId());
			;
			carDto.setLink(foto.getLink());

		}

		return carDto;
	}

}
