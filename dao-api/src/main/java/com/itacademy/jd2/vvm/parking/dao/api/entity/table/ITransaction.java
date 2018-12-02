package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

public interface ITransaction extends IBaseEntity {

	IClient getClient();

	void setClient(IClient client);

	Integer getValue();

	void setValue(Integer value);

	String getDescription();

	void setDescription(String description);
}
