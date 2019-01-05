package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IClient;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.web.dto.ClientDTO;

@Component
public class ClientToDTOConverter implements Function<IClient, ClientDTO> {

	@Override
	public ClientDTO apply(final IClient entity) {
		final ClientDTO clientDto = new ClientDTO();
		clientDto.setId(entity.getId());

		clientDto.setCreated(entity.getCreated());
		clientDto.setUpdated(entity.getUpdated());

		final IUserAccount userAccount = entity.getUserAccount();
		if (userAccount != null) {
			clientDto.setUserAccountId(userAccount.getId());
			clientDto.setUserAccountFirstName(userAccount.getFirstName());
			clientDto.setUserAccountLastName(userAccount.getLastName());

		}

		final ICar car = entity.getCar();
		if (car != null) {
			clientDto.setCarId(car.getId());
			clientDto.setCarNumber(car.getNumber());
			final IModel model = car.getModel();
			if (model != null) {
				clientDto.setCarModelId(model.getId());
				clientDto.setCarModelName(model.getName());
			}

		}

		final ITariff tariff = entity.getTariff();

		if (tariff != null) {
			clientDto.setTariffId(tariff.getId());

			clientDto.setTariffName(tariff.getName());

		}

		return clientDto;
	}

}
