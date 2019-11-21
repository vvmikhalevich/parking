package com.itacademy.jd2.vvm.parking.dao.orm.impl;

import com.itacademy.jd2.vvm.parking.dao.api.IUserAccountDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.vvm.parking.dao.api.filter.UserAccountFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.UserAccount;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.UserAccount_;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {

	protected UserAccountDaoImpl() {
		super(UserAccount.class);
	}

	@Override
	public IUserAccount createEntity() {
		final UserAccount userAccount = new UserAccount();
		return userAccount;
	}

	@Override
	public List<IUserAccount> find(UserAccountFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		// create empty query and define returning type
		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class);

		// define target entity(table)
		final Root<UserAccount> from = cq.from(UserAccount.class); // select from UserAccount

		// define what will be added to result set
		cq.select(from); // select * from UserAccount

		applyFilter(filter, cb, cq, from);

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IUserAccount> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<IUserAccount> resultList = q.getResultList();
		return resultList;
	}

	private Path<?> getSortPath(final Root<UserAccount> from, final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(UserAccount_.id);
		case "firstName":
			return from.get(UserAccount_.firstName);
		case "lastName":
			return from.get(UserAccount_.lastName);
		case "role":
			return from.get(UserAccount_.role);
		case "email":
			return from.get(UserAccount_.email);
		case "password":
			return from.get(UserAccount_.password);
		case "created":
			return from.get(UserAccount_.created);
		case "updated":
			return from.get(UserAccount_.updated);

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(UserAccountFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<UserAccount> from = cq.from(UserAccount.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public IUserAccount findByLogin(String username) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class);

		final Root<UserAccount> from = cq.from(UserAccount.class);

		cq.select(from);

		cq.where(cb.equal(from.get(UserAccount_.email), username));

		final TypedQuery<IUserAccount> q = em.createQuery(cq);
		return getSingleResult(q);
	}

	private void applyFilter(final UserAccountFilter filter, final CriteriaBuilder cb, final CriteriaQuery<?> cq,
			final Root<UserAccount> from) {

		final String email = filter.getUserEmail();

		final List<Predicate> ands = new ArrayList<>();

		if (email != null) {
			ands.add(cb.equal(from.get(UserAccount_.email), email));

		}

		if (!ands.isEmpty()) {
			cq.where(cb.and(ands.toArray(new Predicate[0])));
		}

	}

}
