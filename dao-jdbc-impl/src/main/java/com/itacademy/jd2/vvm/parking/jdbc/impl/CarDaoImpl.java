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
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.filter.CarFilter;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Car;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Model;
import com.itacademy.jd2.vvm.parking.jdbc.impl.util.PreparedStatementAction;

@Repository
public class CarDaoImpl extends AbstractDaoImpl<ICar, Integer> implements ICarDao {

	private IModelDao modelDao;

	@Autowired
	public CarDaoImpl(IModelDao modelDao) {
		super();
		this.modelDao = modelDao;
	}

	@Override
	public ICar createEntity() {
		return new Car();
	}

	@Override
	public void insert(final ICar entity) {
		executeStatement(new PreparedStatementAction<ICar>(
				String.format("insert into %s (vin, sold, sold_date, model_id, created, updated) values(?,?,?,?,?,?)",
						getTableName()),
				true) {
			@Override
			public ICar doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getVin());
				pStmt.setBoolean(2, entity.getSold());
				pStmt.setObject(3, entity.getSoldDate(), Types.TIMESTAMP);
				pStmt.setInt(4, entity.getModel().getId());
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
		executeStatement(new PreparedStatementAction<ICar>(String
				.format("update %s set vin=?, updated=?, sold=?, sold_date=?, model_id=? where id=?", getTableName())) {
			@Override
			public ICar doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getVin());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setBoolean(3, entity.getSold());
				pStmt.setObject(4, entity.getSoldDate(), Types.TIMESTAMP);
				pStmt.setInt(5, entity.getModel().getId());
				pStmt.setInt(6, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected ICar parseRow(final ResultSet resultSet) throws SQLException {
		final ICar entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setSold(resultSet.getBoolean("sold"));
		entity.setVin(resultSet.getString("vin"));
		entity.setSoldDate(resultSet.getTimestamp("sold_date"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final IModel model = new Model();
		model.setId((Integer) resultSet.getObject("model_id"));
		entity.setModel(model);
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

		return car;
	}

	@Override
	protected String getTableName() {
		return "car";
	}
}
