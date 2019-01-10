package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.web.dto.EventDTO;

@Component
public class EventToDTOConverter implements Function<IEvent, EventDTO> {

	@Override
	public EventDTO apply(final IEvent entity) {
		final EventDTO dto = new EventDTO();
		dto.setId(entity.getId());
		dto.setTimeStart(entity.getTimeStart());
		dto.setTimeStartTime(entity.getTimeStart());
		dto.setTimeEnd(entity.getTimeEnd());
		dto.setTimeEndTime(entity.getTimeEnd());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		final IPlace place = entity.getPlace();
		if (place != null) {
			dto.setPlaceId(place.getId());
			dto.setPlaceName(place.getName());

		}

		final ICar car = entity.getCar();
		if (car != null) {
			dto.setCarId(car.getId());
			dto.setCarNumber(car.getNumber());
		}

		return dto;
	}

}
