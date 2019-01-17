package com.itacademy.jd2.vvm.parking.dao.orm.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.itacademy.jd2.vvm.parking.dao.api.IPlaceDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IPlace;
import com.itacademy.jd2.vvm.parking.dao.api.filter.PlaceFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Car_;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Parking_;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Place;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Place_;

@Repository
public class PlaceDaoImpl extends AbstractDaoImpl<IPlace, Integer> implements IPlaceDao {

	protected PlaceDaoImpl() {
		super(Place.class);
	}

	@Override
	public IPlace createEntity() {
		Place place = new Place();
		return place;
	}

	@Override
	public List<IPlace> find(PlaceFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IPlace> cq = cb.createQuery(IPlace.class);

		final Root<Place> from = cq.from(Place.class);
		cq.select(from); // select what? select *

		from.fetch(Place_.parking, JoinType.LEFT);
		from.fetch(Place_.car, JoinType.LEFT);

		applyFilter(filter, cb, cq, from);

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

		final TypedQuery<IPlace> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private Path<?> getSortPath(final Root<Place> from, final String sortColumn) {
		switch (sortColumn) {

		case "id":
			return from.get(Place_.id);
		case "name":
			return from.get(Place_.name);
		case "parking_id":
			return from.get(Place_.parking).get(Parking_.name);
		case "car_id":
			return from.get(Place_.car).get(Car_.number);

		case "created":
			return from.get(Place_.created);
		case "updated":
			return from.get(Place_.updated);

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	private void applyFilter(final PlaceFilter filter, final CriteriaBuilder cb, final CriteriaQuery<?> cq,
			final Root<Place> from) {
		final List<Predicate> ands = new ArrayList<>();

		final Integer id = filter.getParkingId();
		if (!StringUtils.isEmpty(id)) {
			ands.add(cb.equal(from.get(Place_.parking), id));
		}

		if (!ands.isEmpty()) {
			cq.where(cb.and(ands.toArray(new Predicate[0])));
		}
	}

	@Override
	public long getCount(PlaceFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Place> from = cq.from(Place.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public IPlace getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IPlace> cq = cb.createQuery(IPlace.class); // define returning result
		final Root<Place> from = cq.from(Place.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Place_.parking, JoinType.LEFT);
		from.fetch(Place_.car, JoinType.LEFT);

		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(Place_.id), id)); // where id=?

		final TypedQuery<IPlace> q = em.createQuery(cq);

		return getSingleResult(q);
	}

}
