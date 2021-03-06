package com.itacademy.jd2.vvm.parking.web.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.ParkingType;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IParking;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ParkingFilter;
import com.itacademy.jd2.vvm.parking.dao.api.filter.PlaceFilter;
import com.itacademy.jd2.vvm.parking.service.ICarService;
import com.itacademy.jd2.vvm.parking.service.IParkingService;
import com.itacademy.jd2.vvm.parking.service.IPlaceService;
import com.itacademy.jd2.vvm.parking.web.converter.ParkingFromDTOConverter;
import com.itacademy.jd2.vvm.parking.web.converter.ParkingToDTOConverter;
import com.itacademy.jd2.vvm.parking.web.converter.PlaceToDTOConverter;
import com.itacademy.jd2.vvm.parking.web.dto.ParkingDTO;
import com.itacademy.jd2.vvm.parking.web.dto.PlaceDTO;
import com.itacademy.jd2.vvm.parking.web.dto.UserAccountDTO;
import com.itacademy.jd2.vvm.parking.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.vvm.parking.web.security.AuthHelper;

@Controller
@RequestMapping(value = "/parking")
public class ParkingController extends AbstractController {

	@Autowired
	private ICarService carService;

	@Autowired
	private IPlaceService placeService;

	@Autowired
	private IParkingService parkingService;

	@Autowired
	private ParkingToDTOConverter toDtoConverter;

	@Autowired
	private ParkingFromDTOConverter fromDtoConverter;

	@Autowired
	private PlaceToDTOConverter placeToDtoConverter;

	@Autowired
	private ParkingController(IParkingService parkingService, ParkingToDTOConverter toDtoConverter,
			ParkingFromDTOConverter fromDtoConverter) {
		super();
		this.parkingService = parkingService;
		this.toDtoConverter = toDtoConverter;
		this.fromDtoConverter = fromDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {
		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		final ParkingFilter filter = new ParkingFilter();
		prepareFilter(gridState, filter);

		final List<IParking> entities = parkingService.find(filter);
		List<ParkingDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(parkingService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("parking.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();

		loadCommonFormModels(hashMap);

		Integer loggedUserId = AuthHelper.getLoggedUserId();

		if (loggedUserId == null) {
			return new ModelAndView("parking.list");
		}

		hashMap.put("formModel", new ParkingDTO());

		return new ModelAndView("parking.edit", hashMap);
	}

	@RequestMapping(value = "/addPlaces/{id}", method = RequestMethod.GET)
	public ModelAndView showFormPlaces(@PathVariable(name = "id", required = true) final Integer id) {
		final Map<String, Object> hashMap = new HashMap<>();
		final IParking dbModel = parkingService.get(id);
		hashMap.put("formModel", toDtoConverter.apply(dbModel));

		return new ModelAndView("addPlace.edit", hashMap);
	}

	@RequestMapping(value = "/addPlaces/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> savePlaces(@RequestBody Map<String, Boolean> jsonData,
			@PathVariable(name = "id", required = true) final Integer id) {
		System.out.println("map : " + jsonData);
		System.out.println("id parking : " + id);

		Iterator<Entry<String, Boolean>> i = jsonData.entrySet().iterator();
		while (i.hasNext()) {
			Entry<String, Boolean> e = i.next();
			if (e != null) {
				String name = (String) e.getKey();
				Boolean value = (Boolean) e.getValue();
				System.out.println(name + "=" + value);
				if (value) {

					final IPlace entity = placeService.createEntity();
					entity.setName(name);
					final IParking parking = parkingService.get(id);
					parking.setId(id);

					entity.setParking(parking);
					// final ICar car = carService.get(1);
					// entity.setCar(car);

					final Date date = new Date();
					entity.setCreated(date);
					entity.setUpdated(date);

					placeService.save(entity);

				}

			}
		}

		final IParking parking = parkingService.get(id);
		parking.setStatus(ParkingType.valueOf("enable"));
		;
		parkingService.save(parking);

		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final ParkingDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "parking.edit";
		} else {
			Integer loggedUserId = AuthHelper.getLoggedUserId();

			if (loggedUserId == null) {
				return "redirect:/parking";
			}
			final IParking entity = fromDtoConverter.apply(formModel);
			parkingService.save(entity);
			return "redirect:/parking"; // generates 302 response with Location="/parking/parking"
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		parkingService.delete(id);
		return "redirect:/parking";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IParking parking = parkingService.get(id);
		final ParkingDTO dto = toDtoConverter.apply(parking);

		final PlaceFilter filter = new PlaceFilter();
		filter.setParkingId(id);

		final List<IPlace> entities = placeService.find(filter);

		Iterator<IPlace> i = entities.iterator();

		final Map<String, IPlace> places = new HashMap<>();
		final Map<String, PlaceDTO> placesDto = new HashMap<>();

		for (IPlace iPlace : entities) {

			// TODO new SomeDtoWithInfoYouNeedOnJSP
			places.put(iPlace.getName(), iPlace);

			PlaceDTO placeDto = placeToDtoConverter.apply(iPlace);

			placesDto.put(iPlace.getName(), placeDto);

		}

		final Map<String, Object> hashMap = new HashMap<>();

		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		hashMap.put("places", placesDto);// map (placeId, PlaceDetailsDto)

		return new ModelAndView("addPlace.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final ParkingDTO dto = toDtoConverter.apply(parkingService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		loadCommonFormModels(hashMap);

		return new ModelAndView("parking.edit", hashMap);
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {

		final List<ParkingType> roleTypesList = Arrays.asList(ParkingType.values());
		final Map<String, String> parkingTypesMap = roleTypesList.stream()
				.collect(Collectors.toMap(ParkingType::name, ParkingType::name));
		hashMap.put("statusesChoices", parkingTypesMap);

	}

}
