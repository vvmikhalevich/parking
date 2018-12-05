package com.itacademy.jd2.vvm.parking.web.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IBrand;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.web.dto.ModelDTO;

@Component
public class ModelToDTOConverter implements Function<IModel, ModelDTO> {

	@Override
	public ModelDTO apply(final IModel entity) {
		final ModelDTO modelDto = new ModelDTO();
		modelDto.setId(entity.getId());
		modelDto.setName(entity.getName());
		modelDto.setCreated(entity.getCreated());
		modelDto.setUpdated(entity.getUpdated());
		final IBrand brand = entity.getBrand();
		if (brand != null) {
			modelDto.setBrandId(brand.getId());
			modelDto.setBrandName(brand.getName());

		}

		return modelDto;
	}

}
