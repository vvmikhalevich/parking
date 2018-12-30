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

import com.itacademy.jd2.vvm.parking.dao.api.IClientDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IClient;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ClientFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Car_;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Client;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Client_;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Model_;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Tariff_;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.UserAccount_;

@Repository
public class ClientDaoImpl extends AbstractDaoImpl<IClient, Integer> implements IClientDao {

	protected ClientDaoImpl() {
		super(Client.class);
	}

	@Override
	public IClient createEntity() {
		Client client = new Client();
		return client;
	}

	@Override
	public List<IClient> find(ClientFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IClient> cq = cb.createQuery(IClient.class);

		final Root<Client> from = cq.from(Client.class);
		cq.select(from); // select what? select *

		from.fetch(Client_.car, JoinType.LEFT).fetch(Car_.model, JoinType.LEFT).fetch(Model_.brand, JoinType.LEFT);

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

		final TypedQuery<IClient> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private Path<?> getSortPath(final Root<Client> from, final String sortColumn) {
		switch (sortColumn) {

		case "id":
			return from.get(Client_.id);
		case "user_account":
			return from.get(Client_.userAccount).get(UserAccount_.firstName);
		case "car":
			return from.get(Client_.car).get(Car_.number);
		case "tariff":
			return from.get(Client_.tariff).get(Tariff_.name);
		case "created":
			return from.get(Client_.created);
		case "updated":
			return from.get(Client_.updated);

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(ClientFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Client> from = cq.from(Client.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public IClient getFullInfo(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IClient> cq = cb.createQuery(IClient.class); // define returning result
		final Root<Client> from = cq.from(Client.class); // define table for select

		cq.select(from); // define what need to be selected

		from.fetch(Client_.car, JoinType.LEFT).fetch(Car_.model, JoinType.LEFT).fetch(Model_.brand, JoinType.LEFT);

		from.fetch(Client_.userAccount, JoinType.LEFT);

		cq.distinct(true); // to avoid duplicate rows in result

		// .. where id=...
		cq.where(cb.equal(from.get(Client_.id), id)); // where id=?

		final TypedQuery<IClient> q = em.createQuery(cq);

		return getSingleResult(q);
	}

}
