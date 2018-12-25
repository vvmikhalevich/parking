package com.itacademy.jd2.vvm.parking.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.ITariffDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;
import com.itacademy.jd2.vvm.parking.dao.api.filter.TariffFilter;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Tariff;
import com.itacademy.jd2.vvm.parking.jdbc.impl.util.PreparedStatementAction;

@Repository
public class TariffDaoImpl extends AbstractDaoImpl<ITariff, Integer> implements ITariffDao {

	@Override
	public ITariff createEntity() {
		return new Tariff();
	}

	@Override
	public void insert(final ITariff entity) {
		executeStatement(new PreparedStatementAction<ITariff>(String.format(
				"insert into %s (name, price, status, created, updated) values(?,?,?,?,?)", getTableName()), true) {
			@Override
			public ITariff doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getPrice());
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
	public void update(final ITariff entity) {
		executeStatement(new PreparedStatementAction<ITariff>(
				String.format("update %s set name=?, price=?, status=? updated=? where id=?", getTableName())) {
			@Override
			public ITariff doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getPrice());
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
	protected ITariff parseRow(final ResultSet resultSet) throws SQLException {
		final ITariff entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setPrice((Integer) resultSet.getObject("price"));
		entity.setStatus(resultSet.getString("status"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	public List<ITariff> find(final TariffFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(final TariffFilter filter) {
		return executeCountQuery("");
	}

	public ITariff getFullInfo(Integer id) {
		final ITariff tariff = get(id);

		return tariff;
	}

}
