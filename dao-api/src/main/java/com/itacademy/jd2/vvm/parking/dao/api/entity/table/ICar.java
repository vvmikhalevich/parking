package com.itacademy.jd2.vvm.parking.dao.api.entity.table;

import java.util.Date;

public interface ICar extends IBaseEntity {

    void setSoldDate(Date soldDate);

    Date getSoldDate();

    void setSold(Boolean sold);

    Boolean getSold();

    void setModel(IModel model);

    IModel getModel();

    void setVin(String vin);

    String getVin();
}
