package com.itacademy.jd2.vvm.parking.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;

public class FotoServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final IFoto entity = saveNewFoto();

		final IFoto entityFromDb = fotoService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getLink(), entityFromDb.getLink());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final IFoto entity = saveNewFoto();

		String newName = entity.getLink() + "_updated";
		entity.setLink(newName);
		Thread.sleep(2000);
		fotoService.save(entity);

		final IFoto entityFromDb = fotoService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(newName, entityFromDb.getLink());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testGetAll() {
		final int intialCount = fotoService.getAll().size();

		final int randomObjectsCount = getRandomObjectsCount();
		for (int i = 0; i < randomObjectsCount; i++) {
			saveNewFoto();
		}

		final List<IFoto> allEntities = fotoService.getAll();

		for (final IFoto entityFromDb : allEntities) {
			assertNotNull(entityFromDb.getLink());
			assertNotNull(entityFromDb.getId());
			assertNotNull(entityFromDb.getCreated());
			assertNotNull(entityFromDb.getUpdated());
		}

		assertEquals(randomObjectsCount + intialCount, allEntities.size());
	}

	@Test
	public void testDelete() {
		final IFoto entity = saveNewFoto();
		fotoService.delete(entity.getId());
		assertNull(fotoService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewFoto();
		fotoService.deleteAll();
		assertEquals(0, fotoService.getAll().size());
	}
}
