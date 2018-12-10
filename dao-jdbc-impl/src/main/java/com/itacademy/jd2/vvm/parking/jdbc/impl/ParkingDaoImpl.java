package com.itacademy.jd2.vvm.parking.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.IParkingDao;
import com.itacademy.jd2.vvm.parking.dao.api.ITariffDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IParking;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ParkingFilter;
import com.itacademy.jd2.vvm.parking.dao.api.filter.TariffFilter;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Parking;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Tariff;
import com.itacademy.jd2.vvm.parking.jdbc.impl.util.PreparedStatementAction;

@Repository
public class ParkingDaoImpl extends AbstractDaoImpl<IParking, Integer> implements IParkingDao {

	@Override
	public IParking createEntity() {
		return new Parking();
	}

	@Override
	public void insert(final IParking entity) {
		executeStatement(new PreparedStatementAction<IParking>(String.format(
				"insert into %s (name, adress, status, created, updated) values(?,?,?,?,?)", getTableName()), true) {
			@Override
			public IParking doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getAdress());
				pStmt.setString(3, entity.getStatus());
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
	public void update(final IParking entity) {
		executeStatement(new PreparedStatementAction<IParking>(
				String.format("update %s set name=?, adress=?, status=? updated=? where id=?", getTableName())) {
			@Override
			public IParking doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setString(2, entity.getAdress());
				pStmt.setString(3, entity.getStatus());
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected String getTableName() {
		return "brand";
	}

	@Override
	protected IParking parseRow(final ResultSet resultSet) throws SQLException {
		final IParking entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setAdress(resultSet.getString("adress"));
		entity.setStatus(resultSet.getString("status"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	public List<IParking> find(final ParkingFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(final ParkingFilter filter) {
		return executeCountQuery("");
	}

	@Override
	public IParking getFullInfo(Integer id) {
		final IParking parking = get(id);

		return parking;
	}

}
