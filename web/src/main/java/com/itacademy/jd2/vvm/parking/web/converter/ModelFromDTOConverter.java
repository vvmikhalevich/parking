package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IBrand;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.service.IBrandService;
import com.itacademy.jd2.vvm.parking.service.IModelService;
import com.itacademy.jd2.vvm.parking.web.dto.BrandDTO;
import com.itacademy.jd2.vvm.parking.web.dto.ModelDTO;

@Component
public class ModelFromDTOConverter implements Function<ModelDTO, IModel> {

	@Autowired
	private IModelService modelService;

	@Autowired
	private IBrandService brandService;

	@Override
	public IModel apply(final ModelDTO dto) {
		final IModel entity = modelService.createEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());

		final IBrand brand = brandService.createEntity();
		brand.setId(dto.getBrandId());
		entity.setBrand(brand);

		return entity;
	}
}
