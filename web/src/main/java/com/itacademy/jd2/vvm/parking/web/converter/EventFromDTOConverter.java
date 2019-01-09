package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.service.ICarService;
import com.itacademy.jd2.vvm.parking.service.IEventService;
import com.itacademy.jd2.vvm.parking.service.IPlaceService;
import com.itacademy.jd2.vvm.parking.web.dto.EventDTO;

@Component
public class EventFromDTOConverter implements Function<EventDTO, IEvent> {

	@Autowired
	private IEventService eventService;

	@Autowired
	private IPlaceService placeService;

	@Autowired
	private ICarService carService;

	@Override
	public IEvent apply(final EventDTO dto) {
		final IEvent entity = eventService.createEntity();
		entity.setId(dto.getId());
		entity.setTimeStart(dto.getTimeStart());
		entity.setTimeEnd(dto.getTimeEnd());

		final IPlace place = placeService.createEntity();
		place.setId(dto.getPlaceId());
		place.setName(dto.getPlaceName());
		entity.setPlace(place);

		final ICar car = carService.createEntity();
		car.setId(dto.getCarId());
		car.setNumber(dto.getCarNumber());

		entity.setCar(car);

		return entity;
	}
}
