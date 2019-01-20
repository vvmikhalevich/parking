package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.ParkingType;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IParking;
import com.itacademy.jd2.vvm.parking.service.IParkingService;
import com.itacademy.jd2.vvm.parking.web.dto.ParkingDTO;

@Component
public class ParkingFromDTOConverter implements Function<ParkingDTO, IParking> {

	@Autowired
	private IParkingService parkingService;

	@Override
	public IParking apply(final ParkingDTO dto) {
		final IParking entity = parkingService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setAdress(dto.getAdress());
		entity.setWidth(dto.getWidth());
		entity.setLength(dto.getLength());
		entity.setCostPerDay(dto.getCostPerDay());
		entity.setStatus(ParkingType.valueOf(dto.getStatus()));
		return entity;
	}
}
