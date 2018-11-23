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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.vvm.parking.web.converter.BrandFromDTOConverter;
import com.itacademy.jd2.vvm.parking.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IBrand;
import com.itacademy.jd2.vvm.parking.dao.api.filter.BrandFilter;
import com.itacademy.jd2.vvm.parking.service.IBrandService;
import com.itacademy.jd2.vvm.parking.web.converter.BrandToDTOConverter;
import com.itacademy.jd2.vvm.parking.web.dto.BrandDTO;

@Controller
@RequestMapping(value = "/brand")
public class BrandController extends AbstractController {

	private IBrandService brandService;

	private BrandToDTOConverter toDtoConverter;
	private BrandFromDTOConverter fromDtoConverter;

	@Autowired
	private BrandController(IBrandService brandService, BrandToDTOConverter toDtoConverter,
			BrandFromDTOConverter fromDtoConverter) {
		super();
		this.brandService = brandService;
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

		final BrandFilter filter = new BrandFilter();
		prepareFilter(gridState, filter);

		final List<IBrand> entities = brandService.find(filter);
		List<BrandDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(brandService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("brand.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IBrand newEntity = brandService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("brand.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final BrandDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "brand.edit";
		} else {
			final IBrand entity = fromDtoConverter.apply(formModel);
			brandService.save(entity);
			return "redirect:/brand"; // generates 302 response with Location="/carsdealer/brand"
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		brandService.delete(id);
		return "redirect:/brand";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IBrand dbModel = brandService.get(id);
		final BrandDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("brand.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final BrandDTO dto = toDtoConverter.apply(brandService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("brand.edit", hashMap);
	}

}
