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

import com.itacademy.jd2.vvm.parking.dao.api.IEventDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IEvent;
import com.itacademy.jd2.vvm.parking.dao.api.filter.EventFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Car_;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Event;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Event_;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Place_;

@Repository
public class EventDaoImpl extends AbstractDaoImpl<IEvent, Integer> implements IEventDao {

	protected EventDaoImpl() {
		super(Event.class);
	}

	@Override
	public IEvent createEntity() {
		Event event = new Event();
		return event;
	}

	@Override
	public List<IEvent> find(EventFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IEvent> cq = cb.createQuery(IEvent.class);

		final Root<Event> from = cq.from(Event.class);
		cq.select(from); // select what? select *

		from.fetch(Event_.place, JoinType.LEFT);
		from.fetch(Event_.car, JoinType.LEFT);

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

		final TypedQuery<IEvent> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private Path<?> getSortPath(final Root<Event> from, final String sortColumn) {
		switch (sortColumn) {

		case "id":
			return from.get(Event_.id);
		case "car":
			return from.get(Event_.car).get(Car_.number);
		case "place":
			return from.get(Event_.place).get(Place_.name);
		case "time_start":
			return from.get(Event_.timeStart);
		case "time_end":
			return from.get(Event_.timeEnd);
		case "created":
			return from.get(Event_.created);
		case "updated":
			return from.get(Event_.updated);

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(EventFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Event> from = cq.from(Event.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public IEvent getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IEvent> cq = cb.createQuery(IEvent.class); // define returning result
		final Root<Event> from = cq.from(Event.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Event_.place, JoinType.LEFT);
		from.fetch(Event_.car, JoinType.LEFT);

		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(Event_.id), id)); // where id=?

		final TypedQuery<IEvent> q = em.createQuery(cq);

		return getSingleResult(q);
	}

}
