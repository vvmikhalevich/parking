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

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;
import com.itacademy.jd2.vvm.parking.dao.api.filter.FotoFilter;
import com.itacademy.jd2.vvm.parking.service.IFotoService;
import com.itacademy.jd2.vvm.parking.web.converter.FotoFromDTOConverter;
import com.itacademy.jd2.vvm.parking.web.converter.FotoToDTOConverter;
import com.itacademy.jd2.vvm.parking.web.dto.FotoDTO;
import com.itacademy.jd2.vvm.parking.web.dto.grid.GridStateDTO;

@Controller
@RequestMapping(value = "/foto")
public class FotoController extends AbstractController {

	private IFotoService fotoService;

	private FotoToDTOConverter toDtoConverter;
	private FotoFromDTOConverter fromDtoConverter;

	@Autowired
	private FotoController(IFotoService fotoService, FotoToDTOConverter toDtoConverter,
			FotoFromDTOConverter fromDtoConverter) {
		super();
		this.fotoService = fotoService;
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

		final FotoFilter filter = new FotoFilter();
		prepareFilter(gridState, filter);

		final List<IFoto> entities = fotoService.find(filter);
		List<FotoDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());
		gridState.setTotalCount(fotoService.getCount(filter));

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("foto.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IFoto newEntity = fotoService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));

		return new ModelAndView("foto.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("formModel") final FotoDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			return "foto.edit";
		} else {
			final IFoto entity = fromDtoConverter.apply(formModel);
			fotoService.save(entity);
			return "redirect:/foto"; // generates 302 response with Location="/parking/foto"
		}
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		fotoService.delete(id);
		return "redirect:/foto";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IFoto dbModel = fotoService.get(id);
		final FotoDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);

		return new ModelAndView("foto.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final FotoDTO dto = toDtoConverter.apply(fotoService.get(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);

		return new ModelAndView("foto.edit", hashMap);
	}

}
