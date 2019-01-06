package com.itacademy.jd2.vvm.parking.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.filter.EventFilter;
import com.itacademy.jd2.vvm.parking.service.ICarService;
import com.itacademy.jd2.vvm.parking.service.IEventService;
import com.itacademy.jd2.vvm.parking.service.IPlaceService;
import com.itacademy.jd2.vvm.parking.web.converter.EventFromDTOConverter;
import com.itacademy.jd2.vvm.parking.web.converter.EventToDTOConverter;
import com.itacademy.jd2.vvm.parking.web.dto.EventDTO;

@Controller
@RequestMapping(value = "/event")
public class EventController extends AbstractController {

	@Autowired
	private IEventService eventService;

	@Autowired
	private IPlaceService placeService;

	@Autowired
	private ICarService carService;

	@Autowired
	private EventFromDTOConverter fromDtoConverter;

	@Autowired
	private EventToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final EventFilter filter = new EventFilter();

		final List<IEvent> entities = eventService.find(filter);
		List<EventDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("event.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IEvent newEntity = eventService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));
		loadCommonFormModels(hashMap);
		return new ModelAndView("event.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final EventDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("event.edit", hashMap);
		} else {
			final IEvent entity = fromDtoConverter.apply(formModel);

			eventService.save(entity);
			return "redirect:/event";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IEvent dbModel = eventService.getFullInfo(id);
		final EventDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("event.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final EventDTO dto = toDtoConverter.apply(eventService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("event.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		eventService.delete(id);
		return "redirect:/event";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IPlace> places = placeService.getAll();
		final List<ICar> cars = carService.getAll();

		final Map<Integer, String> placesMap = places.stream()
				.collect(Collectors.toMap(IPlace::getId, IPlace::getName));
		hashMap.put("placesChoices", placesMap);

		final Map<Integer, String> carsMap = cars.stream().collect(Collectors.toMap(ICar::getId, ICar::getNumber));
		hashMap.put("carsChoices", carsMap);

	}

}
