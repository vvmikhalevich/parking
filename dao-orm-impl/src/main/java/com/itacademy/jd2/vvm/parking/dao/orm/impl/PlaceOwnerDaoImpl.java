package com.itacademy.jd2.vvm.parking.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.IPlaceOwnerDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlaceOwner;
import com.itacademy.jd2.vvm.parking.dao.api.filter.PlaceOwnerFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.PlaceOwner;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.PlaceOwner_;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Place_;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.UserAccount_;

@Repository
public class PlaceOwnerDaoImpl extends AbstractDaoImpl<IPlaceOwner, Integer> implements IPlaceOwnerDao {

	protected PlaceOwnerDaoImpl() {
		super(PlaceOwner.class);
	}

	@Override
	public IPlaceOwner createEntity() {
		PlaceOwner placeOwner = new PlaceOwner();
		return placeOwner;
	}

	@Override
	public List<IPlaceOwner> find(PlaceOwnerFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IPlaceOwner> cq = cb.createQuery(IPlaceOwner.class);

		final Root<PlaceOwner> from = cq.from(PlaceOwner.class);
		cq.select(from); // select what? select *

		from.fetch(PlaceOwner_.place, JoinType.LEFT);
		from.fetch(PlaceOwner_.userAccount, JoinType.LEFT);

		final String sortColumn = filter.getSortColumn();
		if (filter.getSortColumn() != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));// build path to
			// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<IPlaceOwner> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private Path<?> getSortPath(final Root<PlaceOwner> from, final String sortColumn) {
		switch (sortColumn) {

		case "id":
			return from.get(PlaceOwner_.id);
		case "userAccount":
			return from.get(PlaceOwner_.userAccount).get(UserAccount_.firstName);
		case "car":
			return from.get(PlaceOwner_.place).get(Place_.name);

		case "created":
			return from.get(PlaceOwner_.created);
		case "updated":
			return from.get(PlaceOwner_.updated);

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(PlaceOwnerFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<PlaceOwner> from = cq.from(PlaceOwner.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public IPlaceOwner getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IPlaceOwner> cq = cb.createQuery(IPlaceOwner.class); // define returning result
		final Root<PlaceOwner> from = cq.from(PlaceOwner.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(PlaceOwner_.place, JoinType.LEFT);
		from.fetch(PlaceOwner_.userAccount, JoinType.LEFT);

		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(PlaceOwner_.id), id)); // where id=?

		final TypedQuery<IPlaceOwner> q = em.createQuery(cq);

		return getSingleResult(q);
	}

}
