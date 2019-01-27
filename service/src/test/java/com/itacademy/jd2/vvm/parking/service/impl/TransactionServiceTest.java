package com.itacademy.jd2.vvm.parking.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITransaction;

public class TransactionServiceTest extends AbstractTest {

	@Test
	public void testCreate() {
		final ITransaction entity = saveNewTransaction();

		final ITransaction entityFromDb = transactionService.get(entity.getId());

		assertNotNull(entityFromDb);
		// assertEquals(entity.getName(), entityFromDb.getName());
		assertEquals(entity.getValue(), entityFromDb.getValue());
		assertEquals(entity.getDescription(), entityFromDb.getDescription());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
	}

	@Test
	public void testUpdate() throws InterruptedException {
		final ITransaction entity = saveNewTransaction();

		String newName = entity.getDescription() + "_updated";
		entity.setDescription(newName);
		Thread.sleep(2000);
		transactionService.save(entity);

		final ITransaction entityFromDb = transactionService.get(entity.getId());

		assertNotNull(entityFromDb);
		assertEquals(entity.getValue(), entityFromDb.getValue());
		assertEquals(entity.getDescription(), entityFromDb.getDescription());
		assertNotNull(entityFromDb.getId());
		assertNotNull(entityFromDb.getCreated());
		assertNotNull(entityFromDb.getUpdated());
		assertEquals(entity.getCreated(), entityFromDb.getCreated());
		assertTrue(entityFromDb.getUpdated().after(entity.getCreated()));
	}

	@Test
	public void testDelete() {
		final ITransaction entity = saveNewTransaction();
		transactionService.delete(entity.getId());
		assertNull(transactionService.get(entity.getId()));
	}

	@Test
	public void testDeleteAll() {
		saveNewTransaction();
		transactionService.deleteAll();
		assertEquals(0, transactionService.getAll().size());
	}
}
