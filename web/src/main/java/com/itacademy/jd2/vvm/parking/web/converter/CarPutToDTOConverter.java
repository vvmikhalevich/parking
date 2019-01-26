package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.web.dto.CarDTO;

@Component
public class CarPutToDTOConverter implements Function<ICar, CarDTO> {

	@Override
	public CarDTO apply(final ICar entity) {
		final CarDTO dto = new CarDTO();
		dto.setId(entity.getId());
		dto.setNumber(entity.getNumber());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		final IModel model = entity.getModel();
		if (model != null) {
			dto.setModelId(model.getId());
			dto.setModelName(model.getName());

		}

		final IUserAccount userAccount = entity.getUserAccount();
		if (userAccount != null) {
			dto.setUserAccountId(userAccount.getId());
			dto.setUserAccountFirstName(userAccount.getFirstName());
			dto.setUserAccountLastName(userAccount.getLastName());

		}

		final ITariff tariff = entity.getTariff();
		if (tariff != null) {
			dto.setTariffId(tariff.getId());
			dto.setTariffName(tariff.getName());

		}

		final IFoto foto = entity.getFoto();
		if (foto != null) {
			dto.setFotoId(foto.getId());
			dto.setLink(foto.getLink());
			dto.setCreated(foto.getCreated());

		}

		return dto;
	}

}
