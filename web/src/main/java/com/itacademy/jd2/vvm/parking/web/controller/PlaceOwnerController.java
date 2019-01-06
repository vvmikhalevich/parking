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

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlaceOwner;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.dao.api.filter.PlaceOwnerFilter;
import com.itacademy.jd2.vvm.parking.service.IPlaceOwnerService;
import com.itacademy.jd2.vvm.parking.service.IPlaceService;
import com.itacademy.jd2.vvm.parking.service.IUserAccountService;
import com.itacademy.jd2.vvm.parking.web.converter.PlaceOwnerFromDTOConverter;
import com.itacademy.jd2.vvm.parking.web.converter.PlaceOwnerToDTOConverter;
import com.itacademy.jd2.vvm.parking.web.dto.PlaceOwnerDTO;

@Controller
@RequestMapping(value = "/placeOwner")
public class PlaceOwnerController extends AbstractController {

	@Autowired
	private IPlaceOwnerService placeOwnerService;

	@Autowired
	private IPlaceService placeService;

	@Autowired
	private IUserAccountService userAccountService;

	@Autowired
	private PlaceOwnerFromDTOConverter fromDtoConverter;

	@Autowired
	private PlaceOwnerToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final PlaceOwnerFilter filter = new PlaceOwnerFilter();

		final List<IPlaceOwner> entities = placeOwnerService.find(filter);
		List<PlaceOwnerDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("placeOwner.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IPlaceOwner newEntity = placeOwnerService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));
		loadCommonFormModels(hashMap);
		return new ModelAndView("placeOwner.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final PlaceOwnerDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("placeOwner.edit", hashMap);
		} else {
			final IPlaceOwner entity = fromDtoConverter.apply(formModel);

			placeOwnerService.save(entity);
			return "redirect:/placeOwner";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IPlaceOwner dbModel = placeOwnerService.getFullInfo(id);
		final PlaceOwnerDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("placeOwner.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final PlaceOwnerDTO dto = toDtoConverter.apply(placeOwnerService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("placeOwner.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		placeOwnerService.delete(id);
		return "redirect:/placeOwner";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IPlace> places = placeService.getAll();
		final List<IUserAccount> users = userAccountService.getAll();

		final Map<Integer, String> placesMap = places.stream()
				.collect(Collectors.toMap(IPlace::getId, IPlace::getName));
		hashMap.put("placesChoices", placesMap);

		final Map<Integer, String> usersMap = users.stream()
				.collect(Collectors.toMap(IUserAccount::getId, IUserAccount::getLastName));
		hashMap.put("usersChoices", usersMap);

	}

}
