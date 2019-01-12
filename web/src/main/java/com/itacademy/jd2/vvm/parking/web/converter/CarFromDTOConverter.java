package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.service.ICarService;
import com.itacademy.jd2.vvm.parking.service.IFotoService;
import com.itacademy.jd2.vvm.parking.service.IModelService;
import com.itacademy.jd2.vvm.parking.web.dto.CarDTO;

@Component
public class CarFromDTOConverter implements Function<CarDTO, ICar> {

	@Autowired
	private ICarService carService;

	@Autowired
	private IModelService modelService;

	@Autowired
	private IFotoService fotoService;

	@Override
	public ICar apply(final CarDTO dto) {
		final ICar entity = carService.createEntity();
		entity.setId(dto.getId());

		final IModel model = modelService.createEntity();
		model.setId(dto.getModelId());
		entity.setModel(model);

		entity.setNumber(dto.getNumber());

		final IFoto foto = fotoService.createEntity();
		foto.setId(dto.getFotoId());
		entity.setFoto(foto);

		return entity;
	}
}
