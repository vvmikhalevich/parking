<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header"><mytaglib:i18n key="table.column.Transactions" /></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesTransaction}"
					column="id"><mytaglib:i18n key="table.column.id" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTransaction}"
					column="user_account"><mytaglib:i18n key="table.column.UserAccount" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTransaction}"
					column="value"><mytaglib:i18n key="table.column.Value" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTransaction}"
					column="description"><mytaglib:i18n key="table.column.Description" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTransaction}"
					column="created">
					<mytaglib:i18n key="table.column.created" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTransaction}"
					column="updated">
					<mytaglib:i18n key="table.column.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="transaction" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${transaction.id}" /></td>

				<td><a href="${pagesUserAccount}/${transaction.userAccountId}">${transaction.userAccountLastName}</a></td>
				<td><c:out value="${transaction.value}" /></td>
				<td><c:out value="${transaction.description}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${transaction.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${transaction.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesTransaction}/${transaction.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="${pagesTransaction}/${transaction.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesTransaction}/${transaction.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right"
	href="${pagesTransaction}/add"><i class="material-icons">add</i></a>
