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

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IBrand;
import com.itacademy.jd2.vvm.parking.dao.api.filter.BrandFilter;
import com.itacademy.jd2.vvm.parking.service.IBrandService;
import com.itacademy.jd2.vvm.parking.web.converter.BrandToDTOConverter;
import com.itacademy.jd2.vvm.parking.web.dto.BrandDTO;

@Controller
@RequestMapping(value = "/brand")
public class BrandController {

	private IBrandService brandService;

	private BrandToDTOConverter toDtoConverter;

	@Autowired
	private BrandController(IBrandService brandService, BrandToDTOConverter toDtoConverter) {
		super();
		this.brandService = brandService;
		this.toDtoConverter = toDtoConverter;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(final HttpServletRequest req) {

		final BrandFilter filter = new BrandFilter();

		final List<IBrand> entities = brandService.find(filter);
		List<BrandDTO> dtos = entities.stream().map(toDtoConverter).collect(Collectors.toList());

		final Map<String, Object> models = new HashMap<>();
		models.put("gridItems", dtos);
		return new ModelAndView("brand.list", models);
	}

}
