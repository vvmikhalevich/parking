package com.itacademy.jd2.vvm.parking.web.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IParking;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.dao.api.filter.EventFilter;
import com.itacademy.jd2.vvm.parking.dao.api.filter.PlaceFilter;
import com.itacademy.jd2.vvm.parking.service.ICarService;
import com.itacademy.jd2.vvm.parking.service.IEventService;
import com.itacademy.jd2.vvm.parking.service.IParkingService;
import com.itacademy.jd2.vvm.parking.service.IPlaceService;
import com.itacademy.jd2.vvm.parking.service.IUserAccountService;
import com.itacademy.jd2.vvm.parking.web.converter.PlaceFromDTOConverter;
import com.itacademy.jd2.vvm.parking.web.converter.PlaceToDTOConverter;
import com.itacademy.jd2.vvm.parking.web.dto.PlaceDTO;
import com.itacademy.jd2.vvm.parking.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.vvm.parking.web.dto.search.PlaceSearchDTO;
import com.itacademy.jd2.vvm.parking.web.security.AuthHelper;

@Controller
@RequestMapping(value = "/place")
public class PlaceController extends AbstractController {

	private static final String SEARCH_FORM_MODEL = "searchFormModel";
	private static final String SEARCH_DTO = PlaceController.class.getSimpleName() + "_SEACH_DTO";

	@Autowired
	private IEventService eventService;

	@Autowired
	private IPlaceService placeService;

	@Autowired
	private IUserAccountService userAccountService;

	@Autowired
	private ICarService carService;

	@Autowired
	private IParkingService parkingService;

	@Autowired
	private PlaceFromDTOConverter fromDtoConverter;

	@Autowired
	private PlaceToDTOConverter toDtoConverter;

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView index(final HttpServletRequest req, @ModelAttribute(SEARCH_FORM_MODEL) PlaceSearchDTO searchDto,
			@RequestParam(name = "page", required = false) final Integer pageNumber,
			@RequestParam(name = "sort", required = false) final String sortColumn) {

		final GridStateDTO gridState = getListDTO(req);
		gridState.setPage(pageNumber);
		gridState.setSort(sortColumn, "id");

		if (req.getMethod().equalsIgnoreCase("get")) {
			// do not use empty payload which comes in case of GET
			searchDto = getSearchDTO(req);
		} else {
			req.getSession().setAttribute(SEARCH_DTO, searchDto);
		}

		final PlaceFilter filter = new PlaceFilter();

		if (AuthHelper.hasRole("admin")) {
			// build filter without restrictions

		} else {
			// build filter with\ restriction by logged userId
			Integer loggedUserId = AuthHelper.getLoggedUserId();
			// filter.setLoggedUserId(loggedUserId);
		}

		if (searchDto.getNumber() != null) {
			filter.setNumber(searchDto.getNumber());
		}

		if (searchDto.getUserAccountLastName() != null) {
			filter.setUserAccountLastName(searchDto.getUserAccountLastName());
		}

		prepareFilter(gridState, filter);

		final List<IPlace> entities = placeService.find(filter);
		List<PlaceDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(placeService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		models.put(SEARCH_FORM_MODEL, searchDto);

		return new ModelAndView("place.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();

		hashMap.put("formModel", new PlaceDTO());
		loadCommonFormModels(hashMap);
		return new ModelAndView("place.edit", hashMap);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final PlaceDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("place.edit", hashMap);
		} else {
			final IPlace entity = fromDtoConverter.apply(formModel);

			placeService.save(entity);
			return "redirect:/place";
		}
	}

	@RequestMapping(value = "/{id}/deleteCar", method = RequestMethod.GET)
	public String deleteCar(@PathVariable(name = "id", required = true) final Integer id) {

		final IPlace entity = placeService.getFullInfo(id);

		entity.setCar(null);

		placeService.save(entity);

		final EventFilter eventFilter = new EventFilter();

		eventFilter.setCarId(id);

		final List<IEvent> events = eventService.find(eventFilter);

		if (events != null) {
			IEvent event = events.get(0);

			Date timeEnd = new Date();
			event.setTimeEnd(timeEnd);

			eventService.save(event);
		}

		return "redirect:/place";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IPlace dbModel = placeService.getFullInfo(id);
		final PlaceDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("place.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final PlaceDTO dto = toDtoConverter.apply(placeService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("place.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		placeService.delete(id);
		return "redirect:/place";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IParking> parkings = parkingService.getAll();
		final List<ICar> cars = carService.getAll();
		final List<IUserAccount> userAccounts = userAccountService.getAll();

		final Map<Integer, String> parkingsMap = parkings.stream()
				.collect(Collectors.toMap(IParking::getId, IParking::getName));
		hashMap.put("parkingsChoices", parkingsMap);

		final Map<Integer, String> carsMap = cars.stream().collect(Collectors.toMap(ICar::getId, ICar::getNumber));
		hashMap.put("carsChoices", carsMap);

		final Map<Integer, String> usersMap = userAccounts.stream()
				.collect(Collectors.toMap(IUserAccount::getId, IUserAccount::getLastName));
		hashMap.put("usersChoices", usersMap);

	}

	private PlaceSearchDTO getSearchDTO(final HttpServletRequest req) {
		PlaceSearchDTO searchDTO = (PlaceSearchDTO) req.getSession().getAttribute(SEARCH_DTO);
		if (searchDTO == null) {
			searchDTO = new PlaceSearchDTO();
			req.getSession().setAttribute(SEARCH_DTO, searchDTO);
		}
		return searchDTO;
	}

}
