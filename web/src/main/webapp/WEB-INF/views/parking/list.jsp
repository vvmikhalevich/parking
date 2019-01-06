<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Parkings</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesParking}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesParking}" column="name">name</mytaglib:sort-link></th>
			<th>adress</th>
			<th><mytaglib:sort-link pageUrl="${pagesParking}"
					column="status">status</mytaglib:sort-link></th>
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="parking" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${parking.id}" /></td>
				<td><c:out value="${parking.name}" /></td>
				<td><c:out value="${parking.adress}" /></td>
				<td><c:out value="${parking.status}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${parking.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${parking.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesParking}/${parking.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesParking}/${parking.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesParking}/${parking.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesParking}/add"><i
	class="material-icons">add</i></a>
