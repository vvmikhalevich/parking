package com.itacademy.jd2.vvm.parking.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IBrand;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;

public class ModelServiceTest extends AbstractTest {

	@Test
	public void createTest() {
		final IModel entity = modelService.createEntity();
		entity.setName("model-" + getRandomPrefix());
		entity.setBrand(saveNewBrand());
		modelService.save(entity);

		final IModel entityFromDb = modelService.get(entity.getId());

		assertEquals(entity.getName(), entityFromDb.getName());
		// assertEquals(entity.getBrand().getId(), entityFromDb.getBrand().getId());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {

		final IModel entity = modelService.createEntity();
		entity.setName("model-" + getRandomPrefix());
		entity.setBrand(saveNewBrand());
		modelService.save(entity);

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(2000);
		modelService.save(entity);

		final IModel entityFromDb = modelService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newName, entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = modelService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewBrand();
		}

		final List<IModel> allEntities = modelService.getAll();

		for (final IModel entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getName());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {

		final IModel entity = modelService.createEntity();
		entity.setName("model-" + getRandomPrefix());
		entity.setBrand(saveNewBrand());
		modelService.save(entity);

		modelService.delete(entity.getId());
		assertNull(brandService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewBrand();
		modelService.deleteAll();
		assertEquals(0, modelService.getAll().size());
	}

}
