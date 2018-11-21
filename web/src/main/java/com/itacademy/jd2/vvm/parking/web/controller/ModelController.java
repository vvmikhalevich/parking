package com.itacademy.jd2.vvm.parking.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ModelFilter;
import com.itacademy.jd2.vvm.parking.service.IModelService;
import com.itacademy.jd2.vvm.parking.web.converter.ModelToDTOConverter;
import com.itacademy.jd2.vvm.parking.web.dto.ModelDTO;

@Controller
@RequestMapping(value = "/model")
public class ModelController {

	private IModelService modelService;

	private ModelToDTOConverter toDtoConverter;

	@Autowired
	private ModelController(IModelService modelService, ModelToDTOConverter toDtoConverter) {
		super();
		this.modelService = modelService;
		this.toDtoConverter = toDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final ModelFilter filter = new ModelFilter();

		final List<IModel> entities = modelService.find(filter);
		List<ModelDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("model.list", models);
	}

}
