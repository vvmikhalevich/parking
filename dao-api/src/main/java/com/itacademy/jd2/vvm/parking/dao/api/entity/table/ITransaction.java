package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

import java.math.BigDecimal;

public interface ITransaction extends IBaseEntity {

	IClient getClient();

	void setClient(IClient client);

	BigDecimal getValue();

	void setValue(BigDecimal value);

	String getDescription();

	void setDescription(String description);
}
