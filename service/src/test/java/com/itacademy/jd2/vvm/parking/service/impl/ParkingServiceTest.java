package com.itacademy.jd2.vvm.parking.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IParking;

public class ParkingServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IParking entity = saveNewParking();

		final IParking entityFromDb = parkingService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getAdress(), entityFromDb.getAdress());
		assertEquals(entity.getStatus(), entityFromDb.getStatus());
		assertEquals(entity.getLength(), entityFromDb.getLength());
		assertEquals(entity.getWidth(), entityFromDb.getWidth());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IParking entity = saveNewParking();

		String newName = entity.getName() + "_updated";
		entity.setName(newName);
		Thread.sleep(2000);
		parkingService.save(entity);

		final IParking entityFromDb = parkingService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newName, entityFromDb.getName());
		assertEquals(entity.getAdress(), entityFromDb.getAdress());
		assertEquals(entity.getStatus(), entityFromDb.getStatus());
		assertEquals(entity.getLength(), entityFromDb.getLength());
		assertEquals(entity.getWidth(), entityFromDb.getWidth());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testDelete() {
		final IParking entity = saveNewParking();
		parkingService.delete(entity.getId());
		assertNull(parkingService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewParking();
		parkingService.deleteAll();
		assertEquals(0, parkingService.getAll().size());
	}
}
