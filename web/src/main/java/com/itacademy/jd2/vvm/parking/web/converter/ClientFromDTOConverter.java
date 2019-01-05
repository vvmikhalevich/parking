package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IClient;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.service.ICarService;
import com.itacademy.jd2.vvm.parking.service.IClientService;
import com.itacademy.jd2.vvm.parking.service.ITariffService;
import com.itacademy.jd2.vvm.parking.service.IUserAccountService;
import com.itacademy.jd2.vvm.parking.web.dto.ClientDTO;

@Component
public class ClientFromDTOConverter implements Function<ClientDTO, IClient> {

	@Autowired
	private IClientService clientService;

	@Autowired
	private ICarService carService;

	@Autowired
	private IUserAccountService userAccountService;

	@Autowired
	private ITariffService tariffService;

	@Override
	public IClient apply(final ClientDTO dto) {
		final IClient entity = clientService.createEntity();
		entity.setId(dto.getId());

		final ICar car = carService.createEntity();
		car.setId(dto.getCarId());
		entity.setCar(car);

		final IUserAccount userAccount = userAccountService.createEntity();
		userAccount.setId(dto.getUserAccountId());
		entity.setUserAccount(userAccount);

		final ITariff tariff = tariffService.createEntity();
		tariff.setId(dto.getTariffId());
		entity.setTariff(tariff);

		return entity;
	}
}
