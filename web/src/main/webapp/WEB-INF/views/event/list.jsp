<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Events</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesEvent}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEvent}" column="car">car(id)</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEvent}" column="place">place(id)</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEvent}"
					column="time_start">time of start</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEvent}"
					column="time_end">time of end</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEvent}" column="created">
					<mytaglib:i18n key="table.column.created" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEvent}" column="updated">
					<mytaglib:i18n key="table.column.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="event" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${event.id}" /></td>

				<td><c:out value="${event.carNumber}(${event.carId})" /></td>

				<td><c:out value="${event.placeName}(${event.placeId})" /></td>


				<td><c:out value="${event.timeStart}" /></td>
				<td><c:out value="${event.timeEnd}" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${event.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${event.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesEvent}/${event.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesEvent}/${event.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesEvent}/${event.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesEvent}/add"><i
	class="material-icons">add</i></a>
