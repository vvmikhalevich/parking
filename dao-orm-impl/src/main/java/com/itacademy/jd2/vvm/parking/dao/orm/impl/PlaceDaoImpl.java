package com.itacademy.jd2.vvm.parking.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

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
		case "status":
			return from.get(Place_.status);
		case "created":
			return from.get(Place_.created);
		case "updated":
			return from.get(Place_.updated);

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
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
		// TODO Auto-generated method stub
		return null;
	}

}
