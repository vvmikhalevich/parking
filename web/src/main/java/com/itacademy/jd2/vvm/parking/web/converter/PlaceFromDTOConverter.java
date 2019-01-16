package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IParking;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.service.ICarService;
import com.itacademy.jd2.vvm.parking.service.IParkingService;
import com.itacademy.jd2.vvm.parking.service.IPlaceService;
import com.itacademy.jd2.vvm.parking.web.dto.PlaceDTO;

@Component
public class PlaceFromDTOConverter implements Function<PlaceDTO, IPlace> {

	@Autowired
	private IPlaceService placeService;

	@Autowired
	private ICarService carService;

	@Autowired
	private IParkingService parkingService;

	@Override
	public IPlace apply(final PlaceDTO dto) {
		final IPlace entity = placeService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());

		final IParking parking = parkingService.createEntity();
		parking.setId(dto.getParkingId());
		parking.setName(dto.getParkingName());
		entity.setParking(parking);

		final ICar car = carService.createEntity();
		car.setId(dto.getCarId());
		car.setNumber(dto.getCarNumber());

		entity.setCar(car);

		return entity;
	}
}
