package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IParking;
import com.itacademy.jd2.vvm.parking.web.dto.ParkingDTO;

@Component
public class ParkingToDTOConverter implements Function<IParking, ParkingDTO> {

	@Override
	public ParkingDTO apply(final IParking entity) {
		final ParkingDTO dto = new ParkingDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setAdress(entity.getAdress());
		dto.setStatus(entity.getStatus());
		dto.setWidth(entity.getWidth());
		dto.setLength(entity.getLength());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());
		return dto;
	}

}
