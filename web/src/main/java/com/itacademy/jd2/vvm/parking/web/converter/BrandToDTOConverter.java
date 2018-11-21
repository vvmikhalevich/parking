package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IBrand;
import com.itacademy.jd2.vvm.parking.web.dto.BrandDTO;

@Component
public class BrandToDTOConverter implements Function<IBrand, BrandDTO> {

	@Override
	public BrandDTO apply(final IBrand entity) {
		final BrandDTO dto = new BrandDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}
