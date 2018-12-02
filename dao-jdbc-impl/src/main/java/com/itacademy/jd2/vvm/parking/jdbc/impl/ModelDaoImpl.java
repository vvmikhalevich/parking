package com.itacademy.jd2.vvm.parking.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.IModelDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ModelFilter;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Brand;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Model;
import com.itacademy.jd2.vvm.parking.jdbc.impl.util.SQLExecutionException;

@Repository
public class ModelDaoImpl extends AbstractDaoImpl<IModel, Integer> implements IModelDao {

	@Override
	public IModel createEntity() {
		return new Model();
	}

	@Override
	public void insert(final IModel entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(String
						.format("insert into %s (name, brand_id, created, updated ) values(?,?,?,?)", getTableName()),
						Statement.RETURN_GENERATED_KEYS)) {
			c.setAutoCommit(false);
			try {
				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getBrand().getId());
				pStmt.setObject(3, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();
				entity.setId(id);
				// the same should be done in 'update' DAO method
				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}
	}

	@Override
	public void update(final IModel entity) {
		try (Connection c = getConnection();
				PreparedStatement pStmt = c.prepareStatement(
						String.format("update %s set name=?, updated=?, brand_id=? where id=?", getTableName()))) {
			c.setAutoCommit(false);
			try {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getBrand().getId());
				pStmt.setInt(4, entity.getId());
				pStmt.executeUpdate();
				// the same should be done in 'update' DAO method
				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}
	}

	@Override
	protected IModel parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final IModel entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final Integer brandId = (Integer) resultSet.getObject("brand_id");
		if (brandId != null) {
			final Brand brand = new Brand();
			brand.setId(brandId);
			if (columns.contains("brand_name")) {
				brand.setName(resultSet.getString("brand_name"));
			}
			entity.setBrand(brand);
		}
		return entity;
	}

	@Override
	public void deleteAll() {
		try (Connection c = getConnection();
				PreparedStatement deleteCarRefsStmt = c.prepareStatement("delete from car");
				PreparedStatement deleteAllStmt = c.prepareStatement("delete from " + getTableName());) {
			c.setAutoCommit(false);
			try {
				deleteCarRefsStmt.executeUpdate();
				deleteAllStmt.executeUpdate();

				deleteCarRefsStmt.close();
				deleteAllStmt.close();

				c.commit();
			} catch (final Exception e) {
				c.rollback();
				throw new RuntimeException(e);
			}

		} catch (final SQLException e) {
			throw new SQLExecutionException(e);
		}

	}

	@Override
	public IModel getFullInfo(final Integer id) {
		final IModel model = get(id);

		return model;
	}

	@Override
	protected String getTableName() {
		return "model";
	}

	@Override
	public List<IModel> find(final ModelFilter filter) {
		final StringBuilder sql;
		if (filter.getFetchBrand()) {
			sql = new StringBuilder(String.format("select model.*, brand.name as brand_name from %s", getTableName()));
		} else {
			sql = new StringBuilder(String.format("select model.* from %s", getTableName()));
		}
		appendJOINs(sql, filter);
		appendWHEREs(sql, filter);
		appendSort(filter, sql);
		appendPaging(filter, sql);
		return executeFindQueryWithCustomSelect(sql.toString());
	}

	@Override
	public long getCount(final ModelFilter filter) {
		final StringBuilder sql = new StringBuilder("");
		appendJOINs(sql, filter);
		appendWHEREs(sql, filter);
		return executeCountQuery(sql.toString());
	}

	private void appendJOINs(final StringBuilder sb, final ModelFilter filter) {
		if (filter.getFetchBrand()) {
			sb.append(" join brand on (brand.id=model.brand_id) ");
		}
	}

	private void appendWHEREs(final StringBuilder sb, final ModelFilter filter) {
		// nothing yet
	}

}
