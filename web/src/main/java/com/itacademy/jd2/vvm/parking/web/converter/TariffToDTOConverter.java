package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;
import com.itacademy.jd2.vvm.parking.web.dto.TariffDTO;

@Component
public class TariffToDTOConverter implements Function<ITariff, TariffDTO> {

	@Override
	public TariffDTO apply(final ITariff entity) {
		final TariffDTO dto = new TariffDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCostPerDay(entity.getCostPerDay());
		dto.setStatus(entity.getStatus().name());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}
