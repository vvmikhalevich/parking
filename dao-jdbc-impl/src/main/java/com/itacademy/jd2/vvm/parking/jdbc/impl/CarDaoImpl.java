package com.itacademy.jd2.vvm.parking.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.ICarDao;
import com.itacademy.jd2.vvm.parking.dao.api.IModelDao;
import com.itacademy.jd2.vvm.parking.dao.api.IUserAccountDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.filter.CarFilter;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Brand;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Car;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Model;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.vvm.parking.jdbc.impl.util.PreparedStatementAction;

@Repository
public class CarDaoImpl extends AbstractDaoImpl<ICar, Integer> implements ICarDao {

	private IModelDao modelDao;
	private IUserAccountDao userAccountDao;
	
	@Autowired
	public CarDaoImpl(IModelDao modelDao, IUserAccountDao userAccountDao) {
		super();
		this.modelDao = modelDao;
		this.userAccountDao = userAccountDao;
	}

	@Override
	public ICar createEntity() {
		return new Car();
	}

	@Override
	public void insert(final ICar entity) {
		executeStatement(new PreparedStatementAction<ICar>(String.format(
				"insert into %s (model_id, number, user_account_id, foto_id, created, updated) values(?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public ICar doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getModel().getId());
				pStmt.setString(2, entity.getNumber());
				pStmt.setInt(3, entity.getUserAccount().getId());
				pStmt.setInt(4, entity.getFoto().getId());
				pStmt.setObject(5, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(6, entity.getUpdated(), Types.TIMESTAMP);

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
	public void update(final ICar entity) {
		executeStatement(new PreparedStatementAction<ICar>(String.format(
				"update %s set model_id=?, number=?, user_account_id=?, foto_id=? where id=?", getTableName())) {
			@Override
			public ICar doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getModel().getId());
				pStmt.setString(2, entity.getNumber());
				pStmt.setInt(3, entity.getUserAccount().getId());
				pStmt.setInt(4, entity.getFoto().getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected ICar parseRow(final ResultSet resultSet) throws SQLException {
		final ICar entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setNumber(resultSet.getString("number"));
		// entity.setUserAccount(resultSet.getInt("user_account_id"));
		// entity.setFoto(resultSet.getInt("foto_id"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final IModel model = new Model();
		model.setId((Integer) resultSet.getObject("model_id"));
		entity.setModel(model);

		final Integer userAccountId = (Integer) resultSet.getObject("user_account_id");
		if (userAccountId != null) {
			final UserAccount userAccount = new UserAccount();
			userAccount.setId(userAccountId);

			entity.setUserAccount(userAccount);

		}

		return entity;
	}

	@Override
	public List<ICar> find(final CarFilter filter) {
		final StringBuilder sql = new StringBuilder("");
		appendSort(filter, sql);
		appendPaging(filter, sql);
		return executeFindQuery(sql.toString());
	}

	@Override
	public long getCount(final CarFilter filter) {
		return executeCountQuery("");
	}

	@Override
	public ICar getFullInfo(final Integer id) {
		final ICar car = get(id);

		if (car.getModel() != null) {
			car.setModel(modelDao.get(car.getModel().getId()));
		}
		// add!!!

		return car;
	}

	@Override
	protected String getTableName() {
		return "car";
	}
}
