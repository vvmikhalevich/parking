package com.itacademy.jd2.vvm.parking.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.IPlaceDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.filter.PlaceFilter;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Car;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Parking;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Place;
import com.itacademy.jd2.vvm.parking.jdbc.impl.util.PreparedStatementAction;

@Repository
public class PlaceDaoImpl extends AbstractDaoImpl<IPlace, Integer> implements IPlaceDao {

	private IPlaceDao placeDao;

	@Autowired
	public PlaceDaoImpl(IPlaceDao placeDao) {
		super();
		this.placeDao = placeDao;
	}

	@Override
	public IPlace createEntity() {
		return new Place();
	}

	@Override
	public void insert(final IPlace entity) {
		executeStatement(new PreparedStatementAction<IPlace>(
				String.format("insert into %s (name, parking_id, car_id, status, created, updated) values(?,?,?,?,?,?)",
						getTableName()),
				true) {
			@Override
			public IPlace doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getParking().getId());
				pStmt.setInt(3, entity.getCar().getId());

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
	public void update(final IPlace entity) {
		executeStatement(new PreparedStatementAction<IPlace>(
				String.format("update %s set name=?, parking_id=?, car_id=?,  updated=? where id=?", getTableName())) {
			@Override
			public IPlace doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getParking().getId());
				pStmt.setInt(3, entity.getCar().getId());

				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected IPlace parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final IPlace entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));

		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final Integer parkingId = (Integer) resultSet.getObject("parking_id");
		if (parkingId != null) {
			final Parking parking = new Parking();
			parking.setId(parkingId);
			entity.setParking(parking);

		}

		final Integer carId = (Integer) resultSet.getObject("car_id");
		if (carId != null) {
			final Car car = new Car();
			car.setId(carId);
			entity.setCar(car);
		}

		return entity;
	}

	@Override
	public List<IPlace> find(final PlaceFilter filter) {
		final StringBuilder sql = new StringBuilder("");
		appendSort(filter, sql);
		appendPaging(filter, sql);
		return executeFindQuery(sql.toString());
	}

	@Override
	public long getCount(final PlaceFilter filter) {
		return executeCountQuery("");
	}

	@Override
	public IPlace getFullInfo(final Integer id) {
		final IPlace client = get(id);

		// add!!!

		return client;
	}

	@Override
	protected String getTableName() {
		return "place";
	}
}
