package com.itacademy.jd2.vvm.parking.web.controller;

import java.util.Arrays;
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

import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.TariffType;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;
import com.itacademy.jd2.vvm.parking.dao.api.filter.TariffFilter;
import com.itacademy.jd2.vvm.parking.service.ITariffService;
import com.itacademy.jd2.vvm.parking.web.converter.TariffFromDTOConverter;
import com.itacademy.jd2.vvm.parking.web.converter.TariffToDTOConverter;
import com.itacademy.jd2.vvm.parking.web.dto.TariffDTO;
import com.itacademy.jd2.vvm.parking.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.vvm.parking.web.security.AuthHelper;

@Controller
@RequestMapping(value = "/tariff")
public class TariffController extends AbstractController {

	private ITariffService tariffService;

	private TariffToDTOConverter toDtoConverter;
	private TariffFromDTOConverter fromDtoConverter;

	@Autowired
	private TariffController(ITariffService tariffService, TariffToDTOConverter toDtoConverter,
			TariffFromDTOConverter fromDtoConverter) {
		super();
		this.tariffService = tariffService;
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

		final TariffFilter filter = new TariffFilter();
		prepareFilter(gridState, filter);

		final List<ITariff> entities = tariffService.find(filter);
		List<TariffDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(tariffService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("tariff.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();

		hashMap.put("formModel", new TariffDTO());
		loadCommonFormModels(hashMap);
		Integer loggedUserId = AuthHelper.getLoggedUserId();

		if (loggedUserId == null) {
			return new ModelAndView("tariff.list");
		}

		return new ModelAndView("tariff.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final TariffDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "tariff.edit";
		} else {
			Integer loggedUserId = AuthHelper.getLoggedUserId();

			if (loggedUserId == null) {
				return "redirect:/tariff";
			}

			final ITariff entity = fromDtoConverter.apply(formModel);
			tariffService.save(entity);
			return "redirect:/tariff"; // generates 302 response with Location="/parking/tariff"
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		tariffService.delete(id);
		return "redirect:/tariff";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final ITariff dbModel = tariffService.get(id);
		final TariffDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		loadCommonFormModels(hashMap);

		return new ModelAndView("tariff.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final TariffDTO dto = toDtoConverter.apply(tariffService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);

		return new ModelAndView("tariff.edit", hashMap);
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {

		final List<TariffType> statusTypesList = Arrays.asList(TariffType.values());
		final Map<String, String> statusTypesMap = statusTypesList.stream()
				.collect(Collectors.toMap(TariffType::name, TariffType::name));
		hashMap.put("statusesChoices", statusTypesMap);
	}

}
