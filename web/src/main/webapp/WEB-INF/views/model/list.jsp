<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h4 class="header">
	<mytaglib:i18n key="table.column.models" />
</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesModel}" column="id">
					<mytaglib:i18n key="table.column.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesModel}" column="name">
					<mytaglib:i18n key="table.column.name" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesModel}" column="brand">
					<mytaglib:i18n key="table.column.brand" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesModel}" column="created">
					<mytaglib:i18n key="table.column.created" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesModel}" column="updated">
					<mytaglib:i18n key="table.column.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="model" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${model.id}" /></td>
				<td><c:out value="${model.name}" /></td>
				<td><c:out value="${model.brandName}" /></td>
				<%-- brand_id!!!!!!--%>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${model.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${model.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesModel}/${model.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesModel}/${model.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesModel}/${model.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesModel}/add"><i
	class="material-icons">add</i></a>
