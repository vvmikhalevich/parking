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

import com.itacademy.jd2.vvm.parking.dao.api.ITransactionDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ITransaction;
import com.itacademy.jd2.vvm.parking.dao.api.filter.TransactionFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Client_;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Transaction;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Transaction_;

@Repository
public class TransactionDaoImpl extends AbstractDaoImpl<ITransaction, Integer> implements ITransactionDao {

	protected TransactionDaoImpl() {
		super(Transaction.class);
	}

	@Override
	public ITransaction createEntity() {
		final Transaction transaction = new Transaction();
		return transaction;
	}

	@Override
	public List<ITransaction> find(TransactionFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		// create empty query and define returning type
		final CriteriaQuery<ITransaction> cq = cb.createQuery(ITransaction.class);

		// define target entity(table)
		final Root<Transaction> from = cq.from(Transaction.class); // select from Transaction

		// define what will be added to result set
		cq.select(from); // select * from Transaction

		/*
		 * if (filter.getFetchBrand()) { // select m, b from model m left join brand b
		 * ... from.fetch(Model_.brand, JoinType.LEFT); }
		 */

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<ITransaction> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<ITransaction> resultList = q.getResultList();
		return resultList;
	}

	private Path<?> getSortPath(final Root<Transaction> from, final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(Transaction_.id);
		case "client_id":
			return from.get(Transaction_.client).get(Client_.id);
		case "value":
			return from.get(Transaction_.value);
		case "description":
			return from.get(Transaction_.description);
		case "created":
			return from.get(Transaction_.created);
		case "updated":
			return from.get(Transaction_.updated);

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(TransactionFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Transaction> from = cq.from(Transaction.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public ITransaction getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
