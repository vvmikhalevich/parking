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

import com.itacademy.jd2.vvm.parking.dao.api.IParkingDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IParking;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ParkingFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Parking;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Parking_;

@Repository
public class ParkingDaoImpl extends AbstractDaoImpl<IParking, Integer> implements IParkingDao {

	protected ParkingDaoImpl() {
		super(Parking.class);
	}

	@Override
	public IParking createEntity() {
		final Parking parking = new Parking();
		return parking;
	}

	@Override
	public List<IParking> find(ParkingFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		// create empty query and define returning type
		final CriteriaQuery<IParking> cq = cb.createQuery(IParking.class);

		// define target entity(table)
		final Root<Parking> from = cq.from(Parking.class); // select from parking

		// define what will be added to result set
		cq.select(from); // select * from parking

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IParking> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<IParking> resultList = q.getResultList();
		return resultList;
	}

	private Path<?> getSortPath(final Root<Parking> from, final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(Parking_.id);
		case "name":
			return from.get(Parking_.name);
		case "adress":
			return from.get(Parking_.adress);
		case "width":
			return from.get(Parking_.width);
		case "length":
			return from.get(Parking_.length);
		case "costPerDay":
			return from.get(Parking_.costPerDay);
		case "status":
			return from.get(Parking_.status);
		case "created":
			return from.get(Parking_.created);
		case "updated":
			return from.get(Parking_.updated);

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(ParkingFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		final Root<Parking> from = cq.from(Parking.class); // select from parking
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

	@Override
	public IParking getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
