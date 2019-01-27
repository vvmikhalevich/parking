package com.itacademy.jd2.vvm.parking.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;

public class TariffServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final ITariff entity = saveNewTariff();

		final ITariff entityFromDb = tariffService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getCostPerDay(), entityFromDb.getCostPerDay());
		assertEquals(entity.getStatus(), entityFromDb.getStatus());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	

	@Test
	public void testUpdate() throws InterruptedException {
		final ITariff entity = saveNewTariff();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(2000);
		tariffService.save(entity);

		final ITariff entityFromDb = tariffService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newName, entityFromDb.getName());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}



	@Test
	public void testDelete() {
		final ITariff entity = saveNewTariff();
		tariffService.delete(entity.getId());
		assertNull(tariffService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewTariff();
		tariffService.deleteAll();
		assertEquals(0, tariffService.getAll().size());
	}
}
