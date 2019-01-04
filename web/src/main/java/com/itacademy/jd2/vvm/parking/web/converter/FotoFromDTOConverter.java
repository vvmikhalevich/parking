package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;
import com.itacademy.jd2.vvm.parking.service.IFotoService;
import com.itacademy.jd2.vvm.parking.web.dto.FotoDTO;

@Component
public class FotoFromDTOConverter implements Function<FotoDTO, IFoto> {

	@Autowired
	private IFotoService fotoService;

	@Override
	public IFoto apply(final FotoDTO dto) {
		final IFoto entity = fotoService.createEntity();
		entity.setId(dto.getId());
		entity.setLink(dto.getLink());
		return entity;
	}
}
