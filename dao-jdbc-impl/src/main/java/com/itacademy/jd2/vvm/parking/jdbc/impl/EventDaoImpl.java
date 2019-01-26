package com.itacademy.jd2.vvm.parking.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.IEventDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vvm.parking.dao.api.filter.EventFilter;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Car;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Event;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Place;
import com.itacademy.jd2.vvm.parking.jdbc.impl.util.PreparedStatementAction;

@Repository
public class EventDaoImpl extends AbstractDaoImpl<IEvent, Integer> implements IEventDao {

	private IEventDao eventDao;

	@Autowired
	public EventDaoImpl(IEventDao eventDao) {
		super();
		this.eventDao = eventDao;
	}

	@Override
	public IEvent createEntity() {
		return new Event();
	}

	@Override
	public void insert(final IEvent entity) {
		executeStatement(new PreparedStatementAction<IEvent>(String.format(
				"insert into %s (car_id, place_id, time_start, time_end, created, updated) values(?,?,?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public IEvent doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getCar().getId());
				pStmt.setInt(2, entity.getPlace().getId());
				pStmt.setObject(3, entity.getTimeStart(), Types.TIMESTAMP);
				pStmt.setObject(4, entity.getTimeEnd(), Types.TIMESTAMP);
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
	public void update(final IEvent entity) {
		executeStatement(new PreparedStatementAction<IEvent>(
				String.format("update %s set car_id=?, palace_id=?, time_start=?, time_end=?, updated=? where id=?",
						getTableName())) {
			@Override
			public IEvent doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getCar().getId());
				pStmt.setInt(2, entity.getPlace().getId());
				pStmt.setObject(3, entity.getTimeStart(), Types.TIMESTAMP);
				pStmt.setObject(4, entity.getTimeEnd(), Types.TIMESTAMP);
				pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected IEvent parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final IEvent entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));

		entity.setTimeStart(resultSet.getTimestamp("time_start"));
		entity.setTimeEnd(resultSet.getTimestamp("time_end"));

		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final Integer placeId = (Integer) resultSet.getObject("place_id");
		if (placeId != null) {
			final Place place = new Place();
			place.setId(placeId);

			entity.setPlace(place);
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
	public List<IEvent> find(final EventFilter filter) {
		final StringBuilder sql = new StringBuilder("");
		appendSort(filter, sql);
		appendPaging(filter, sql);
		return executeFindQuery(sql.toString());
	}

	@Override
	public long getCount(final EventFilter filter) {
		return executeCountQuery("");
	}

	@Override
	public IEvent getFullInfo(final Integer id) {
		final IEvent event = get(id);

		// add!!!

		return event;
	}

	@Override
	protected String getTableName() {
		return "event";
	}

	@Override
	public IEvent findByPlace(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
