package com.itacademy.jd2.vvm.parking.web.tag;

public class SortLink {/* extends SimpleTagSupport {

	private String column;

	private String pageUrl;

	@Override
	public void doTag() throws JspException, IOException {
		final JspContext jspContext = getJspContext();

		final GridStateDTO listDto = (GridStateDTO) getJspContext().findAttribute(GridStateDTO.GRID_STATE_SESSION_KEY);
		if (listDto == null) {
			throw new IllegalArgumentException(
					"context should have required attribute in session:" + GridStateDTO.GRID_STATE_SESSION_KEY);
		}

		final SortDTO sort = listDto.getSort();
		final String sortOrder = ((sort != null) && sort.isAscending()) ? "desc" : "asc";
		String sortColumn = null;
		if (sort != null) {
			sortColumn = sort.getColumn();
		}

		final String href = String.format("%s?sort=%s:%s", pageUrl, column, sortOrder);

		final StringWriter tagBodyWriter = new StringWriter();
		getJspBody().invoke(tagBodyWriter); // copy tag body defined in jsp

		String sortIcon;

		if (column.equals(sortColumn)) {
			sortIcon = sort.isAscending() ? "<i class=\"material-icons\">keyboard_arrow_down</i>"
					: "<i class=\"material-icons\">keyboard_arrow_up</i>";
		} else {
			sortIcon = "";
		}

		jspContext.getOut().println(String.format("<a href=\"%s\">%s%s</a>", href, tagBodyWriter.toString(), sortIcon));
	}

	public void setColumn(final String column) {
		this.column = column;
	}

	public void setPageUrl(final String pageUrl) {
		this.pageUrl = pageUrl;
	}
*/
}
