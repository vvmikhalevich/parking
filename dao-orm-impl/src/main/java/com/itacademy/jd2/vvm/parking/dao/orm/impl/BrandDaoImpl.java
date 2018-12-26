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

import com.itacademy.jd2.vvm.parking.dao.api.IBrandDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IBrand;
import com.itacademy.jd2.vvm.parking.dao.api.filter.BrandFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Brand;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Brand_;

@Repository
public class BrandDaoImpl extends AbstractDaoImpl<IBrand, Integer> implements IBrandDao {

	protected BrandDaoImpl() {
		super(Brand.class);
	}

	@Override
	public IBrand createEntity() {
		final Brand brand = new Brand();
		return brand;
	}

	@Override
	public void save(IBrand... entities) {
		throw new RuntimeException("unsupported method");

	}

	@Override
	public List<IBrand> find(BrandFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IBrand> cq = cb.createQuery(IBrand.class); // define
																		// type
																		// of
		// result
		final Root<Brand> from = cq.from(Brand.class);// select from brand
		cq.select(from); // select what? select *

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {

			final Path<?> expression = getSortPath(from, sortColumn); // build path to
			// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<IBrand> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();

	}

	private Path<?> getSortPath(final Root<Brand> from, final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(Brand_.id);
		case "name":
			return from.get(Brand_.name);
		case "created":
			return from.get(Brand_.created);
		case "updated":
			return from.get(Brand_.updated);

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(BrandFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<Brand> from = cq.from(Brand.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

}
