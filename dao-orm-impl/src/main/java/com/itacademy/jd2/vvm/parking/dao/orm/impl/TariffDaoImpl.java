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

import com.itacademy.jd2.vvm.parking.dao.api.ITariffDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITariff;
import com.itacademy.jd2.vvm.parking.dao.api.filter.TariffFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Tariff;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Tariff_;

@Repository
public class TariffDaoImpl extends AbstractDaoImpl<ITariff, Integer> implements ITariffDao {

	protected TariffDaoImpl() {
		super(Tariff.class);
	}

	@Override
	public ITariff createEntity() {
		final Tariff tariff = new Tariff();
		return tariff;
	}

	@Override
	public List<ITariff> find(TariffFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		// create empty query and define returning type
		final CriteriaQuery<ITariff> cq = cb.createQuery(ITariff.class);

		// define target entity(table)
		final Root<Tariff> from = cq.from(Tariff.class); // select from tariff

		// define what will be added to result set
		cq.select(from); // select * from tariff

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<ITariff> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<ITariff> resultList = q.getResultList();
		return resultList;
	}

	private Path<?> getSortPath(final Root<Tariff> from, final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(Tariff_.id);
		case "name":
			return from.get(Tariff_.name);
		case "price":
			return from.get(Tariff_.price);
		case "status":
			return from.get(Tariff_.status);
		case "created":
			return from.get(Tariff_.created);
		case "updated":
			return from.get(Tariff_.updated);

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(TariffFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<Tariff> from = cq.from(Tariff.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

}
