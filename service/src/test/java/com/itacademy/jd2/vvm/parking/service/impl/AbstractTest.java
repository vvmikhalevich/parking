package com.itacademy.jd2.vvm.parking.service.impl;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IBrand;

import com.itacademy.jd2.vvm.parking.service.IBrandService;

import com.itacademy.jd2.vvm.parking.service.IModelService;

@SpringJUnitConfig(locations = "classpath:service-context.xml")
public abstract class AbstractTest {
	@Autowired
	protected IBrandService brandService;
	@Autowired
	protected IModelService modelService;

	private static final Random RANDOM = new Random();

	@BeforeEach
	public void setUpMethod() {
		// clean DB recursive
		modelService.deleteAll();
		brandService.deleteAll();

	}

	protected String getRandomPrefix() {
		return RANDOM.nextInt(99999) + "";
	}

	protected int getRandomObjectsCount() {
		return RANDOM.nextInt(9) + 1;
	}

	public Random getRANDOM() {
		return RANDOM;
	}

	protected IBrand saveNewBrand() {
		final IBrand entity = brandService.createEntity();
		entity.setName("brand-" + getRandomPrefix());
		brandService.save(entity);
		return entity;
	}

}
