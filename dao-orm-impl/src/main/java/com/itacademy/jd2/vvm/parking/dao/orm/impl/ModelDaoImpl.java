package com.itacademy.jd2.vvm.parking.dao.orm.impl;

import com.itacademy.jd2.vvm.parking.dao.api.IModelDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ModelFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Brand_;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Model;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Model_;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

@Repository
public class ModelDaoImpl extends AbstractDaoImpl<IModel, Integer> implements IModelDao {

	protected ModelDaoImpl() {
		super(Model.class);
	}

	@Override
	public IModel createEntity() {
		return new Model();
	}

	@Override
	public IModel getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IModel> cq = cb.createQuery(IModel.class); // define returning result
		final Root<Model> from = cq.from(Model.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Model_.brand, JoinType.LEFT);
		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(Model_.id), id)); // where id=?

		final TypedQuery<IModel> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<IModel> find(final ModelFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		// create empty query and define returning type
		final CriteriaQuery<IModel> cq = cb.createQuery(IModel.class);

		// define target entity(table)
		final Root<Model> from = cq.from(Model.class); // select from model

		// define what will be added to result set
		cq.select(from); // select * from model
		from.fetch(Model_.brand, JoinType.LEFT);
		if (filter.getFetchBrand()) {
			// select m, b from model m left join brand b ...
			// from.fetch(Model_.brand, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IModel> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<IModel> resultList = q.getResultList();
		return resultList;
	}

	private Path<?> getSortPath(final Root<Model> from, final String sortColumn) {
		switch (sortColumn) {
		case "name":
			return from.get(Model_.name);
		case "created":
			return from.get(Model_.created);
		case "updated":
			return from.get(Model_.updated);
		case "id":
			return from.get(Model_.id);
		case "brand":
			return from.get(Model_.brand).get(Brand_.name);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(final ModelFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Model> from = cq.from(Model.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

}
