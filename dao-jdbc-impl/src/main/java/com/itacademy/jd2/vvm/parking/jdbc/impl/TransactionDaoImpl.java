package com.itacademy.jd2.vvm.parking.jdbc.impl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.ITransactionDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.vvm.parking.dao.api.filter.TransactionFilter;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Transaction;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.vvm.parking.jdbc.impl.util.PreparedStatementAction;

@Repository
public class TransactionDaoImpl extends AbstractDaoImpl<ITransaction, Integer> implements ITransactionDao {

	@Override
	public ITransaction createEntity() {
		return new Transaction();
	}

	@Override
	public void insert(final ITransaction entity) {
		executeStatement(new PreparedStatementAction<ITransaction>(String.format(
				"insert into %s (user_account_id, value, description, created, updated) values(?,?,?,?,?)",
				getTableName()), true) {
			@Override
			public ITransaction doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getUserAccount().getId());
				pStmt.setBigDecimal(2, entity.getValue());
				pStmt.setString(3, entity.getDescription());
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
	public void update(final ITransaction entity) {
		executeStatement(new PreparedStatementAction<ITransaction>(String
				.format("update %s set client_id=?, value=?, description=? updated=? where id=?", getTableName())) {
			@Override
			public ITransaction doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getUserAccount().getId());
				pStmt.setBigDecimal(2, entity.getValue());
				pStmt.setString(3, entity.getDescription());
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected String getTableName() {
		return "transaction";
	}

	@Override
	protected ITransaction parseRow(final ResultSet resultSet) throws SQLException {
		final ITransaction entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		// entity.setClient(resultSet.getObject("client_id"));
		entity.setValue((BigDecimal) resultSet.getObject("price"));
		entity.setDescription(resultSet.getString("description"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final Integer userAccountId = (Integer) resultSet.getObject("user_account_id");
		if (userAccountId != null) {
			final UserAccount userAccount = new UserAccount();
			userAccount.setId(userAccountId);

			entity.setUserAccount(userAccount);
		}

		return entity;
	}

	@Override
	public List<ITransaction> find(final TransactionFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(final TransactionFilter filter) {
		return executeCountQuery("");
	}

	@Override
	public ITransaction getFullInfo(Integer id) {
		final ITransaction transaction = get(id);

		return transaction;
	}

}
