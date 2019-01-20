package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.service.ICarService;
import com.itacademy.jd2.vvm.parking.service.IFotoService;
import com.itacademy.jd2.vvm.parking.service.IModelService;
import com.itacademy.jd2.vvm.parking.service.ITariffService;
import com.itacademy.jd2.vvm.parking.service.IUserAccountService;
import com.itacademy.jd2.vvm.parking.web.dto.CarDTO;

@Component
public class CarFromDTOConverter implements Function<CarDTO, ICar> {

	@Autowired
	private ITariffService tariffService;

	@Autowired
	private ICarService carService;

	@Autowired
	private IModelService modelService;

	@Autowired
	private IFotoService fotoService;

	@Autowired
	private IUserAccountService userAccountService;

	@Override
	public ICar apply(final CarDTO dto) {
		final ICar entity = carService.createEntity();
		entity.setId(dto.getId());

		final IModel model = modelService.createEntity();
		model.setId(dto.getModelId());
		entity.setModel(model);

		entity.setNumber(dto.getNumber());

		final IUserAccount userAccount = userAccountService.createEntity();
		userAccount.setId(dto.getUserAccountId());
		userAccount.setFirstName(dto.getUserAccountFirstName());
		userAccount.setLastName(dto.getUserAccountLastName());

		entity.setUserAccount(userAccount);

		final ITariff tariff = tariffService.createEntity();
		tariff.setId(dto.getTariffId());
		tariff.setName(dto.getTariffName());

		entity.setTariff(tariff);

		final IFoto foto = fotoService.createEntity();
		if (dto.getFotoId() != null) {
			foto.setId(dto.getFotoId());
			foto.setLink(dto.getLink());
			foto.setCreated(dto.getCreated());
			entity.setFoto(foto);
		}

		return entity;
	}
}
