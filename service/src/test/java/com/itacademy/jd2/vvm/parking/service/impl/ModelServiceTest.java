package com.itacademy.jd2.vvm.parking.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModelInfo;

public class ModelServiceTest extends AbstractTest {

	@Test
	public void createTest() {
		final IModel entity = modelService.createEntity();
		entity.setName("model-" + getRandomPrefix());
		entity.setBrand(saveNewBrand());
		modelService.save(entity);

		final IModel entityFromDb = modelService.get(entity.getId());

		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getBrand().getId(), entityFromDb.getBrand().getId());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	/*
	 * @Test public void createModelWithEnginesTest() { final IModel entity =
	 * modelService.createEntity(); entity.setName("model-" + getRandomPrefix());
	 * entity.setBrand(saveNewBrand());
	 * 
	 * final int randomObjectsCount = getRandomObjectsCount();
	 * 
	 * modelService.save(entity);
	 * 
	 * final IModel entityFromDb = modelService.getFullInfo(entity.getId());
	 * 
	 * // check that correct (by id) engines were returned
	 * 
	 * }
	 */

	/*
	 * @Test public void createWithModelInfoTest() { final IModel entity =
	 * modelService.createEntity(); entity.setName("model-" + getRandomPrefix());
	 * entity.setBrand(saveNewBrand());
	 * 
	 * final IModelInfo infoEntity = modelService.createInfoEntity();
	 * infoEntity.setDescription("description-" + getRandomPrefix());
	 * modelService.save(entity, infoEntity);
	 * 
	 * final IModel entityFromDb = modelService.getFullInfo(entity.getId());
	 * 
	 * assertEquals(entity.getName(), entityFromDb.getName());
	 * assertEquals(entity.getBrand().getId(), entityFromDb.getBrand().getId());
	 * assertNotNull(entityFromDb.getId());
	 * assertNotNull(entityFromDb.getCreated());
	 * assertNotNull(entityFromDb.getUpdated());
	 * assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	 * 
	 * final IModelInfo infoEntityFromDb = entityFromDb.getModelInfo();
	 * assertNotNull(infoEntityFromDb.getId());
	 * assertEquals(infoEntity.getDescription(), infoEntityFromDb.getDescription());
	 * assertNotNull(infoEntityFromDb.getCreated());
	 * assertNotNull(infoEntityFromDb.getUpdated());
	 * assertTrue(infoEntityFromDb.getCreated().equals(infoEntityFromDb.getUpdated()
	 * )); }
	 */
}
