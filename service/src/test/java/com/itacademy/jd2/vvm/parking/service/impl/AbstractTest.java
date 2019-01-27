package com.itacademy.jd2.vvm.parking.service.impl;

import java.math.BigDecimal;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.ParkingType;
import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.RoleType;
import com.itacademy.jd2.vvm.parking.dao.api.entity.enums.TariffType;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IBrand;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IParking;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.service.IBrandService;
import com.itacademy.jd2.vvm.parking.service.ICarService;
import com.itacademy.jd2.vvm.parking.service.IEventService;
import com.itacademy.jd2.vvm.parking.service.IFotoService;
import com.itacademy.jd2.vvm.parking.service.IModelService;
import com.itacademy.jd2.vvm.parking.service.IParkingService;
import com.itacademy.jd2.vvm.parking.service.IPlaceService;
import com.itacademy.jd2.vvm.parking.service.ITariffService;
import com.itacademy.jd2.vvm.parking.service.ITransactionService;
import com.itacademy.jd2.vvm.parking.service.IUserAccountService;

@SpringJUnitConfig(locations = "classpath:service-context-test.xml")
public abstract class AbstractTest {
	@Autowired
	protected IBrandService brandService;
	@Autowired
	protected IModelService modelService;
	@Autowired
	protected IParkingService parkingService;
	@Autowired
	protected ITariffService tariffService;
	@Autowired
	protected IFotoService fotoService;
	@Autowired
	protected ICarService carService;
	@Autowired
	protected IUserAccountService userAccountService;
	@Autowired
	protected ITransactionService transactionService;
	@Autowired
	protected IPlaceService placeService;
	@Autowired
	protected IEventService eventService;

	private static final Random RANDOM = new Random();

	@BeforeEach
	public void setUpMethod() {
		// clean DB recursive

		carService.deleteAll();
		modelService.deleteAll();
		brandService.deleteAll();

		tariffService.deleteAll();
		fotoService.deleteAll();

		userAccountService.deleteAll();
		transactionService.deleteAll();

		placeService.deleteAll();
		eventService.deleteAll();

		parkingService.deleteAll();

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

	protected ITariff saveNewTariff() {
		final ITariff entity = tariffService.createEntity();
		entity.setName("tariff-" + getRandomPrefix());
		entity.setCostPerDay(new BigDecimal("0"));
		entity.setStatus(TariffType.enable);
		tariffService.save(entity);
		return entity;
	}

	protected ITransaction saveNewTransaction() {
		final ITransaction entity = transactionService.createEntity();
		// entity.setUserAccount(userAccount);
		entity.setValue(new BigDecimal("0"));
		entity.setDescription("transaction");
		transactionService.save(entity);
		return entity;
	}

	protected IFoto saveNewFoto() {
		final IFoto entity = fotoService.createEntity();
		entity.setLink("foto-" + getRandomPrefix());
		fotoService.save(entity);
		return entity;
	}

	protected IUserAccount saveNewUserAccount() {
		final IUserAccount entity = userAccountService.createEntity();
		entity.setFirstName("userF-" + getRandomPrefix());
		entity.setLastName("userL-" + getRandomPrefix());
		entity.setRole(RoleType.admin);
		entity.setEmail("admin");
		entity.setPassword("admin");
		userAccountService.save(entity);
		return entity;
	}

	protected IParking saveNewParking() {
		final IParking entity = parkingService.createEntity();
		entity.setName("parking-" + getRandomPrefix());

		entity.setAdress("grodno-" + getRandomPrefix());
		entity.setLength(5);
		entity.setWidth(5);
		entity.setStatus(ParkingType.enable);
		parkingService.save(entity);
		return entity;
	}
}
