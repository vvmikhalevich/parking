package com.itacademy.jd2.vvm.parking.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.IClientDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IClient;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ClientFilter;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Car;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Client;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Tariff;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.vvm.parking.jdbc.impl.util.PreparedStatementAction;

@Repository
public class ClientDaoImpl extends AbstractDaoImpl<IClient, Integer> implements IClientDao {

	private IClientDao clientDao;

	@Autowired
	public ClientDaoImpl(IClientDao clientDao) {
		super();
		this.clientDao = clientDao;
	}

	@Override
	public IClient createEntity() {
		return new Client();
	}

	@Override
	public void insert(final IClient entity) {
		executeStatement(new PreparedStatementAction<IClient>(String.format(
				"insert into %s (user_account_id, car_id, tariff_id, created, updated) values(?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public IClient doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getUserAccount().getId());
				pStmt.setInt(2, entity.getCar().getId());
				pStmt.setInt(3, entity.getTariff().getId());
				pStmt.setObject(4, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();

				entity.setId(id);
				return entity;
			}
		});
	}

	@Override
	public void update(final IClient entity) {
		executeStatement(new PreparedStatementAction<IClient>(String.format(
				"update %s set user_account_id=?, car_id=?, tariff_id=?, updated=? where id=?", getTableName())) {
			@Override
			public IClient doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getUserAccount().getId());
				pStmt.setInt(2, entity.getCar().getId());
				pStmt.setInt(3, entity.getTariff().getId());
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected IClient parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final IClient entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		// entity.setNumber(resultSet.getString("number"));
		// entity.setUserAccount(resultSet.getInt("user_account_id"));
		// entity.setFoto(resultSet.getInt("foto_id"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final Integer userAccountId = (Integer) resultSet.getObject("user_account_id");
		if (userAccountId != null) {
			final UserAccount userAccount = new UserAccount();
			userAccount.setId(userAccountId);
			entity.setUserAccount(userAccount);

		}

		final Integer carId = (Integer) resultSet.getObject("car_id");
		if (carId != null) {
			final Car car = new Car();
			car.setId(carId);
			entity.setCar(car);
		}

		final Integer tariffId = (Integer) resultSet.getObject("tariff_id");
		if (tariffId != null) {
			final Tariff tariff = new Tariff();
			tariff.setId(tariffId);
			entity.setTariff(tariff);
		}

		return entity;
	}

	@Override
	public List<IClient> find(final ClientFilter filter) {
		final StringBuilder sql = new StringBuilder("");
		appendSort(filter, sql);
		appendPaging(filter, sql);
		return executeFindQuery(sql.toString());
	}

	@Override
	public long getCount(final ClientFilter filter) {
		return executeCountQuery("");
	}

	@Override
	public IClient getFullInfo(final Integer id) {
		final IClient client = get(id);

		// add!!!

		return client;
	}

	@Override
	protected String getTableName() {
		return "client";
	}
}
