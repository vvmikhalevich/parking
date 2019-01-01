package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;
import com.itacademy.jd2.vvm.parking.service.ITariffService;
import com.itacademy.jd2.vvm.parking.web.dto.TariffDTO;

@Component
public class TariffFromDTOConverter implements Function<TariffDTO, ITariff> {

	@Autowired
	private ITariffService tariffService;

	@Override
	public ITariff apply(final TariffDTO dto) {
		final ITariff entity = tariffService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setPrice(dto.getPrice());
		entity.setStatus(dto.getStatus());
		return entity;
	}
}
