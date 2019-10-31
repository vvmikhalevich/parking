package com.itacademy.jd2.vvm.parking.web.controller;

import com.itacademy.jd2.vvm.parking.dao.api.filter.AbstractFilter;
import com.itacademy.jd2.vvm.parking.web.dto.grid.GridStateDTO;
import com.itacademy.jd2.vvm.parking.web.dto.grid.SortDTO;

import javax.servlet.http.HttpServletRequest;

// New
public abstract class AbstractController {
	protected GridStateDTO getListDTO(final HttpServletRequest req) {
		final String sessionModelName = getClass().getSimpleName() + "_GRID_STATE";

		GridStateDTO gridState = (GridStateDTO) req.getSession().getAttribute(sessionModelName);
		if (gridState == null) {
			gridState = new GridStateDTO();
			req.getSession().setAttribute(sessionModelName, gridState);
		}
		req.setAttribute(GridStateDTO.GRID_STATE_SESSION_KEY, gridState);
		return gridState;
	}

	protected void prepareFilter(GridStateDTO gridState, AbstractFilter filter) {
		filter.setLimit(gridState.getItemsPerPage());
		int offset = gridState.getItemsPerPage() * (gridState.getPage() - 1);
		filter.setOffset(gridState.getTotalCount() < offset ? 0 : offset);

		final SortDTO sortModel = gridState.getSort();
		if (sortModel != null) {
			filter.setSortColumn(sortModel.getColumn());
			filter.setSortOrder(sortModel.isAscending());
		}
	}

}
