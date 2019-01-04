<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Fotos</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesFoto}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesFoto}" column="link">link</mytaglib:sort-link></th>
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="foto" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${foto.id}" /></td>
				<td><c:out value="${foto.link}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${foto.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${foto.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesFoto}/${foto.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesFoto}/${foto.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesFoto}/${foto.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesFoto}/add"><i
	class="material-icons">add</i></a>
