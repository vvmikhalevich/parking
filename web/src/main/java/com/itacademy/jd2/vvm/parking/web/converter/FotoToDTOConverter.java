package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;
import com.itacademy.jd2.vvm.parking.web.dto.FotoDTO;

@Component
public class FotoToDTOConverter implements Function<IFoto, FotoDTO> {

	@Override
	public FotoDTO apply(final IFoto entity) {
		final FotoDTO dto = new FotoDTO();
		dto.setId(entity.getId());
		dto.setLink(entity.getLink());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}
