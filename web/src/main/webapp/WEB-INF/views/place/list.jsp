<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h4 class="header">Places</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
			<th>name</th>
			<th>parking(parking_id)</th>
			<th>car(car_id)</th>

			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="place" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${place.id}" /></td>

				<td><c:out value="${place.name}" /></td>

				<td><c:out
						value="${place.parkingName}(parkingId=${place.parkingId})" /></td>

				<td><c:out value="${place.carNumber}(carId=${place.carId})" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${place.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${place.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesPlace}/${place.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesPlace}/${place.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesPlace}/${place.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${pagesPlace}/add"><i
	class="material-icons">add</i></a>
