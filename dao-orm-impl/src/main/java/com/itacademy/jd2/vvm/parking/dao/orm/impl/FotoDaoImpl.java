package com.itacademy.jd2.vvm.parking.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.vvm.parking.dao.api.IFotoDao;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IFoto;
import com.itacademy.jd2.vvm.parking.dao.api.filter.FotoFilter;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Foto;
import com.itacademy.jd2.vvm.parking.dao.orm.impl.entity.Foto_;

@Repository
public class FotoDaoImpl extends AbstractDaoImpl<IFoto, Integer> implements IFotoDao {

	protected FotoDaoImpl() {
		super(Foto.class);
	}

	@Override
	public IFoto createEntity() {
		final Foto foto = new Foto();
		return foto;
	}

	@Override
	public List<IFoto> find(FotoFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IFoto> cq = cb.createQuery(IFoto.class);

		final Root<Foto> from = cq.from(Foto.class);
		cq.select(from); // select what? select *

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Foto, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<IFoto> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();

	}

	private SingularAttribute<? super Foto, ?> toMetamodelFormat(final String sortColumn) {
		switch (sortColumn) {
		case "created":
			return Foto_.created;
		case "updated":
			return Foto_.updated;
		case "id":
			return Foto_.id;
		case "link":
			return Foto_.link;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

}
