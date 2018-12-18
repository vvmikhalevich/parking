package com.itacademy.jd2.vvm.parking.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.IPlaceOwnerDao;
import com.itacademy.jd2.vvm.parking.dao.api.IUserAccountDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlaceOwner;
import com.itacademy.jd2.vvm.parking.dao.api.filter.PlaceOwnerFilter;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.Place;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.PlaceOwner;
import com.itacademy.jd2.vvm.parking.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.vvm.parking.jdbc.impl.util.PreparedStatementAction;

@Repository
public class PlaceOwnerDaoImpl extends AbstractDaoImpl<IPlaceOwner, Integer> implements IPlaceOwnerDao {

	private IUserAccountDao userAccountDao;

	@Autowired
	public PlaceOwnerDaoImpl(IUserAccountDao userAccountDao) {
		super();
		this.userAccountDao = userAccountDao;
	}

	@Override
	public IPlaceOwner createEntity() {
		return new PlaceOwner();
	}

	@Override
	public void insert(final IPlaceOwner entity) {
		executeStatement(new PreparedStatementAction<IPlaceOwner>(
				String.format("insert into %s (user_account_id, place_id, created, updated) values(?,?,?,?,?)",
						getTableName()),
				true) {
			@Override
			public IPlaceOwner doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getUserAccount().getId());
				pStmt.setInt(2, entity.getPlace().getId());
				pStmt.setObject(3, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);

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
	public void update(final IPlaceOwner entity) {
		executeStatement(new PreparedStatementAction<IPlaceOwner>(
				String.format("update %s set user_account_id=?, place_id=?, updated=? where id=?", getTableName())) {
			@Override
			public IPlaceOwner doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getUserAccount().getId());
				pStmt.setInt(2, entity.getPlace().getId());
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected IPlaceOwner parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final IPlaceOwner entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));

		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final Integer userAccountId = (Integer) resultSet.getObject("user_account_id");
		if (userAccountId != null) {
			final UserAccount userAccount = new UserAccount();
			userAccount.setId(userAccountId);

			entity.setUserAccount(userAccount);

		}

		final Integer placeId = (Integer) resultSet.getObject("place_id");
		if (placeId != null) {
			final Place place = new Place();
			place.setId(placeId);

			entity.setPlace(place);
		}

		return entity;
	}

	@Override
	protected String getTableName() {
		return "place_owner";
	}

	@Override
	public List<IPlaceOwner> find(final PlaceOwnerFilter filter) {
		final StringBuilder sql = new StringBuilder("");
		appendSort(filter, sql);
		appendPaging(filter, sql);
		return executeFindQuery(sql.toString());
	}

	@Override
	public long getCount(final PlaceOwnerFilter filter) {
		return executeCountQuery("");
	}

	@Override
	public IPlaceOwner getFullInfo(final Integer id) {
		final IPlaceOwner placeOwner = get(id);

		// add!!!

		return placeOwner;
	}

}
