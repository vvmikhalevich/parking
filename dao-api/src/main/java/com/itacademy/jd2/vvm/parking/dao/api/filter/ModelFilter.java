package com.itacademy.jd2.vvm.parking.dao.api.filter;

public class ModelFilter extends AbstractFilter {
    private boolean fetchBrand;

    public boolean getFetchBrand() {
        return fetchBrand;
    }

    public void setFetchBrand(final boolean fetchBrand) {
        this.fetchBrand = fetchBrand;
    }
}
