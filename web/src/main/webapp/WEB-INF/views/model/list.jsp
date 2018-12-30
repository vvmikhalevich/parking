<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h4 class="header">Models</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
			<th>name</th>
			<th>brand(brand_id)</th>
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="model" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${model.id}" /></td>
				<td><c:out value="${model.name}" /></td>
				<td><c:out
						value="${model.brandName}(brand_id=${model.brandId})" /></td>
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
<a class="waves-effect waves-light btn right" href="${pagesModel}/add"><i
	class="material-icons">add</i></a>
