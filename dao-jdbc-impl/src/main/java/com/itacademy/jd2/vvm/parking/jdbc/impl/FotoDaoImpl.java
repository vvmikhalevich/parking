package com.itacademy.jd2.vvm.parking.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.IFotoDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Foto;
import com.itacademy.jd2.vvm.parking.jdbc.impl.util.PreparedStatementAction;

@Repository
public class FotoDaoImpl extends AbstractDaoImpl<IFoto, Integer> implements IFotoDao {

	@Override
	public IFoto createEntity() {
		return new Foto();
	}

	@Override
	public void insert(final IFoto entity) {
		executeStatement(new PreparedStatementAction<IFoto>(
				String.format("insert into %s (link, created, updated) values(?,?,?)", getTableName()), true) {
			@Override
			public IFoto doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getLink());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);

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
	public void update(final IFoto entity) {
		executeStatement(new PreparedStatementAction<IFoto>(
				String.format("update %s set link=?, updated=? where id=?", getTableName())) {
			@Override
			public IFoto doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getLink());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected String getTableName() {
		return "foto";
	}

	@Override
	protected IFoto parseRow(final ResultSet resultSet) throws SQLException {
		final IFoto entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setLink(resultSet.getString("link"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

}
