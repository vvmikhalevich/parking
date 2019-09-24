package com.itacademy.jd2.vvm.parking.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.jd2.vvm.parking.dao.api.ITransactionDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.dao.api.filter.TransactionFilter;
import com.itacademy.jd2.vvm.parking.service.ICarService;
import com.itacademy.jd2.vvm.parking.service.ITransactionService;
import com.itacademy.jd2.vvm.parking.service.IUserAccountService;

@Service
public class TransactionServiceImpl implements ITransactionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

	private ITransactionDao dao;

	@Autowired
	private IUserAccountService userAccountService;

	@Autowired
	private ITransactionService transactionService;

	@Autowired
	private ICarService carService;

	@Autowired
	public TransactionServiceImpl(ITransactionDao dao) {
		super();
		this.dao = dao;
	}

	@PostConstruct
	private void emulator() {
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

		service.scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {
				LOGGER.info("execute transaction emulator");

				final List<ICar> entities = carService.getAll();

				for (ICar iCar : entities) {

					ICar car = carService.getFullInfo(iCar.getId());

					Integer userAccountId = car.getUserAccount().getId();

					BigDecimal value = new BigDecimal(0);
					value = value.subtract(car.getTariff().getCostPerDay());

					String number = iCar.getNumber();

					final ITransaction entity = transactionService.createEntity();

					entity.setValue(value);
					entity.setDescription(number + " - " + "списание");

					final IUserAccount userAccount = userAccountService.get(userAccountId);

					entity.setUserAccount(userAccount);

					transactionService.save(entity);

				}

			}
		}, 0,10, TimeUnit.MINUTES);
	}

	@Override
	public ITransaction createEntity() {
		return dao.createEntity();
	}

	@Override
	public void save(final ITransaction entity) {
		final Date modifedOn = new Date();
		entity.setUpdated(modifedOn);
		if (entity.getId() == null) {
			LOGGER.info("new transaction created: {}", entity);
			entity.setCreated(modifedOn);
			dao.insert(entity);
		} else {
			LOGGER.debug("transaction updated: {}", entity);
			dao.update(entity);
		}
	}

	@Override
	public ITransaction get(final Integer id) {
		final ITransaction entity = dao.get(id);
		return entity;
	}

	@Override
	public void delete(final Integer id) {
		dao.delete(id);
	}

	@Override
	public void deleteAll() {
		LOGGER.info("delete all transactions");
		dao.deleteAll();
	}

	@Override
	public List<ITransaction> getAll() {
		final List<ITransaction> all = dao.selectAll();
		return all;
	}

	@Override
	public List<ITransaction> find(TransactionFilter filter) {
		return dao.find(filter);
	}

	@Override
	public ITransaction getFullInfo(Integer id) {
		final ITransaction entity = dao.getFullInfo(id);
		return entity;
	}

	@Override
	public long getCount(TransactionFilter filter) {
		return dao.getCount(filter);
	}

}
