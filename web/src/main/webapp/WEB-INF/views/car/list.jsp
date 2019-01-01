<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h4 class="header">Cars</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
			<th>model(model_id)</th>
			<th>number</th>
			<th>user(user_account_id)</th>
			<th>foto(foto_id)</th>
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="car" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${car.id}" /></td>

				<td><c:out value="${car.modelId})" /></td>

				<td><c:out value="${car.number}" /></td>
				<td><c:out value="${car.userAccount})" /></td>
				<td><c:out value="${car.fotoLink}(model_id=${car.fotoId})" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${car.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${car.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesCar}/${car.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesCar}/${car.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesCar}/${car.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${pagesCar}/add"><i
	class="material-icons">add</i></a>
