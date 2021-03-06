<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">
	<mytaglib:i18n key="table.column.UserAccounts" />
</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesUserAccount}"
					column="id">
					<mytaglib:i18n key="table.column.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUserAccount}"
					column="firstName">
					<mytaglib:i18n key="table.column.FirstName" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUserAccount}"
					column="lastName">
					<mytaglib:i18n key="table.column.LastName" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUserAccount}"
					column="role">
					<mytaglib:i18n key="table.column.Role" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUserAccount}"
					column="email">Email</mytaglib:sort-link></th>

			<th><mytaglib:sort-link pageUrl="${pagesUserAccount}"
					column="created">
					<mytaglib:i18n key="table.column.created" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesUserAccount}"
					column="updated">
					<mytaglib:i18n key="table.column.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="userAccount" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${userAccount.id}" /></td>
				<td><c:out value="${userAccount.firstName}" /></td>
				<td><c:out value="${userAccount.lastName}" /></td>
				<td><c:out value="${userAccount.role}" /></td>
				<td><c:out value="${userAccount.email}" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${userAccount.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${userAccount.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesUserAccount}/${userAccount.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="${pagesUserAccount}/${userAccount.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesUserAccount}/${userAccount.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right"
	href="${pagesUserAccount}/add"><i class="material-icons">add</i></a>
