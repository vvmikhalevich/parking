package com.itacademy.jd2.vvm.parking.dao.api;

import java.util.List;
import com.itacademy.jd2.vvm.parking.dao.api.entity.table.IModel;
import com.itacademy.jd2.vvm.parking.dao.api.filter.ModelFilter;

public interface IModelDao extends IDao<IModel, Integer> {

	List<IModel> find(ModelFilter filter);

	long getCount(ModelFilter filter);
}
