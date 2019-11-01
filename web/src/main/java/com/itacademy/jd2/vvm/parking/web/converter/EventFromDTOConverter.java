package com.itacademy.jd2.vvm.parking.web.converter;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.service.ICarService;
import com.itacademy.jd2.vvm.parking.service.IEventService;
import com.itacademy.jd2.vvm.parking.service.IPlaceService;
import com.itacademy.jd2.vvm.parking.web.dto.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;

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

		entity.setTimeEnd(dto.getTimeEnd());

		final Date timeStart = dto.getTimeStart();
		if (timeStart != null) {
			final Calendar fullDateCalendar = Calendar.getInstance();
			fullDateCalendar.setTime(timeStart);

			final Date timeStartTime = dto.getTimeStartTime();
			if (timeStartTime != null) {
				final Calendar timeCalendar = Calendar.getInstance();
				timeCalendar.setTime(timeStartTime);
				fullDateCalendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
				fullDateCalendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
			}

			entity.setTimeStart(fullDateCalendar.getTime());
		}

		final Date timeEnd = dto.getTimeEnd();
		if (timeEnd != null) {
			final Calendar fullDateCalendar = Calendar.getInstance();
			fullDateCalendar.setTime(timeEnd);

			final Date timeEndTime = dto.getTimeEndTime();
			if (timeEndTime != null) {
				final Calendar timeCalendar = Calendar.getInstance();
				timeCalendar.setTime(timeEndTime);
				fullDateCalendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
				fullDateCalendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
			}

			entity.setTimeEnd(fullDateCalendar.getTime());
		}

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
