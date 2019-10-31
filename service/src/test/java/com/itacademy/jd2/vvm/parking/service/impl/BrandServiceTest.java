package com.itacademy.jd2.vvm.parking.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IBrand;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BrandServiceTest extends AbstractTest {

    @Test
    public void testCreate() {
        final IBrand entity = saveNewBrand();

        final IBrand entityFromDb = brandService.get(entity.getId());

        assertNotNull(entityFromDb);
        assertEquals(entity.getName(), entityFromDb.getName());
        assertNotNull(entityFromDb.getId());
        assertNotNull(entityFromDb.getCreated());
        assertNotNull(entityFromDb.getUpdated());
        assertTrue(entityFromDb.getCreated().equals(entityFromDb.getUpdated()));
    }


    @Test
    public void testUpdate() throws InterruptedException {
        final IBrand entity = saveNewBrand();

        String newName = entity.getName() + "_updated";
        entity.setName(newName);
        Thread.sleep(2000);
        brandService.save(entity);
        final IBrand entityFromDb = brandService.get(entity.getId());

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
        final int intialCount = brandService.getAll().size();

        final int randomObjectsCount = getRandomObjectsCount();
        for (int i = 0; i < randomObjectsCount; i++) {
            saveNewBrand();
        }

        final List<IBrand> allEntities = brandService.getAll();

        for (final IBrand entityFromDb : allEntities) {
            assertNotNull(entityFromDb.getName());
            assertNotNull(entityFromDb.getId());
            assertNotNull(entityFromDb.getCreated());
            assertNotNull(entityFromDb.getUpdated());
        }

        assertEquals(randomObjectsCount + intialCount, allEntities.size());
    }

    @Test
    public void testDelete() {
        final IBrand entity = saveNewBrand();
        brandService.delete(entity.getId());
        assertNull(brandService.get(entity.getId()));
    }

    @Test
    public void testDeleteAll() {
        saveNewBrand();
        brandService.deleteAll();
        assertEquals(0, brandService.getAll().size());
    }
}
