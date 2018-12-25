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

import com.itacademy.jd2.vvm.parking.dao.api.ICarDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.ICar;
import com.itacademy.jd2.vvm.parking.dao.api.filter.CarFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Car;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Car_;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Foto_;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Model_;

@Repository
public class CarDaoImpl extends AbstractDaoImpl<ICar, Integer> implements ICarDao {

	protected CarDaoImpl() {
		super(Car.class);
	}

	@Override
	public ICar createEntity() {
		Car car = new Car();
		return car;
	}

	@Override
	public List<ICar> find(CarFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		// create empty query and define returning type
		final CriteriaQuery<ICar> cq = cb.createQuery(ICar.class);

		// define target entity(table)
		final Root<Car> from = cq.from(Car.class); // select from car

		// define what will be added to result set
		cq.select(from); // select * from car

		from.fetch(Car_.model, JoinType.LEFT);

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<ICar> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<ICar> resultList = q.getResultList();
		return resultList;
	}

	private Path<?> getSortPath(final Root<Car> from, final String sortColumn) {
		switch (sortColumn) {
		case "id":
			return from.get(Car_.id);
		case "model":
			return from.get(Car_.model).get(Model_.name);
		case "number":
			return from.get(Car_.number);
		case "foto":
			return from.get(Car_.foto).get(Foto_.link);
		case "created":
			return from.get(Car_.created);
		case "updated":
			return from.get(Car_.updated);

		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public long getCount(CarFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<Car> from = cq.from(Car.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

	@Override
	public ICar getFullInfo(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
