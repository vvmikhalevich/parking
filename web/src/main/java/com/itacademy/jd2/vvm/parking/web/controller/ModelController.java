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

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IBrand;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ModelFilter;
import com.itacademy.jd2.vvm.parking.service.IBrandService;
import com.itacademy.jd2.vvm.parking.service.IModelService;
import com.itacademy.jd2.vvm.parking.web.converter.ModelFromDTOConverter;
import com.itacademy.jd2.vvm.parking.web.converter.ModelToDTOConverter;
import com.itacademy.jd2.vvm.parking.web.dto.ModelDTO;

@Controller
@RequestMapping(value = "/model")
public class ModelController extends AbstractController {
	@Autowired
	private IModelService modelService;

	@Autowired
	private IBrandService brandService;

	@Autowired
	private ModelFromDTOConverter fromDtoConverter;

	@Autowired
	private ModelToDTOConverter toDtoConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final ModelFilter filter = new ModelFilter();

		final List<IModel> entities = modelService.find(filter);
		List<ModelDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("model.list", models);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		final Map<String, Object> hashMap = new HashMap<>();
		final IModel newEntity = modelService.createEntity();
		hashMap.put("formModel", toDtoConverter.apply(newEntity));
		loadCommonFormModels(hashMap);
		return new ModelAndView("model.edit", hashMap);
	}

	@RequestMapping(method = RequestMethod.POST)
	public Object save(@Valid @ModelAttribute("formModel") final ModelDTO formModel, final BindingResult result) {
		if (result.hasErrors()) {
			final Map<String, Object> hashMap = new HashMap<>();
			hashMap.put("formModel", formModel);
			loadCommonFormModels(hashMap);
			return new ModelAndView("model.edit", hashMap);
		} else {
			final IModel entity = fromDtoConverter.apply(formModel);

			modelService.save(entity);
			return "redirect:/model";
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView viewDetails(@PathVariable(name = "id", required = true) final Integer id) {
		final IModel dbModel = modelService.getFullInfo(id);
		final ModelDTO dto = toDtoConverter.apply(dbModel);
		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		hashMap.put("readonly", true);
		loadCommonFormModels(hashMap);
		return new ModelAndView("model.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(name = "id", required = true) final Integer id) {
		final ModelDTO dto = toDtoConverter.apply(modelService.getFullInfo(id));

		final Map<String, Object> hashMap = new HashMap<>();
		hashMap.put("formModel", dto);
		loadCommonFormModels(hashMap);
		return new ModelAndView("model.edit", hashMap);
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public String delete(@PathVariable(name = "id", required = true) final Integer id) {
		modelService.delete(id);
		return "redirect:/model";
	}

	private void loadCommonFormModels(final Map<String, Object> hashMap) {
		final List<IBrand> brands = brandService.getAll();

		/*
		 * final Map<Integer, String> brandsMap = new HashMap<>(); for (final IBrand
		 * iBrand : brands) { brandsMap.put(iBrand.getId(), iBrand.getName()); }
		 */

		final Map<Integer, String> brandsMap = brands.stream()
				.collect(Collectors.toMap(IBrand::getId, IBrand::getName));
		hashMap.put("brandsChoices", brandsMap);

	}

}
