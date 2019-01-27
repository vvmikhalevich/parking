<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h4 class="header">Tariffs</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesTariff}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTariff}" column="name">name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTariff}"
					column="costPerDay">costPerDay</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTariff}" column="status">status</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTariff}"
					column="created">
					<mytaglib:i18n key="table.column.created" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTariff}"
					column="updated">
					<mytaglib:i18n key="table.column.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="tariff" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${tariff.id}" /></td>
				<td><c:out value="${tariff.name}" /></td>
				<td><c:out value="${tariff.costPerDay}" /></td>
				<td><c:out value="${tariff.status}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${tariff.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${tariff.updated}" /></td>
				<sec:authorize access="hasAnyRole('admin', 'manager')">
					<td class="right"><a class="btn-floating"
						href="${pagesTariff}/${tariff.id}"><i class="material-icons">info</i></a>
						<a class="btn-floating" href="${pagesTariff}/${tariff.id}/edit"><i
							class="material-icons">edit</i></a> <a class="btn-floating red"
						href="${pagesTariff}/${tariff.id}/delete"><i
							class="material-icons">delete</i></a></td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasAnyRole('admin')">
	<a class="waves-effect waves-light btn right" href="${pagesTariff}/add"><i
		class="material-icons">add</i></a>
</sec:authorize>
