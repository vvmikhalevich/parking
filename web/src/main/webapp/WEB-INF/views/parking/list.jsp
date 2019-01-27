<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h4 class="header">Parkings</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesParking}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesParking}" column="name">name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesParking}"
					column="adress">adress</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesParking}" column="width">width</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesParking}"
					column="length">length</mytaglib:sort-link></th>

			<th><mytaglib:sort-link pageUrl="${pagesParking}"
					column="status">status</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesParking}"
					column="created">
					<mytaglib:i18n key="table.column.created" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesParking}"
					column="updated">
					<mytaglib:i18n key="table.column.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="parking" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${parking.id}" /></td>
				<td><c:out value="${parking.name}" /></td>
				<td><c:out value="${parking.adress}" /></td>
				<td><c:out value="${parking.width}" /></td>
				<td><c:out value="${parking.length}" /></td>
				<td><c:out value="${parking.status}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${parking.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${parking.updated}" /></td>

				<c:if test="${parking.status=='created'}">
					<td><a class="waves-effect waves-light btn right"
						href="${pagesParking}/addPlaces/${parking.id}"><i
							class="material-icons">add places</i></a></td>
				</c:if>
				
					<td class="right"><a class="btn-floating"
						href="${pagesParking}/${parking.id}"><i class="material-icons">info</i></a>
						<sec:authorize access="hasAnyRole('admin', 'manager')">
						<a class="btn-floating" href="${pagesParking}/${parking.id}/edit"><i
							class="material-icons">edit</i></a> <a class="btn-floating red"
						href="${pagesParking}/${parking.id}/delete"><i
							class="material-icons">delete</i></a></sec:authorize></td>
				
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasAnyRole('admin')">
	<a class="waves-effect waves-light btn right"
		href="${pagesParking}/add"><i class="material-icons">add</i></a>
</sec:authorize>
