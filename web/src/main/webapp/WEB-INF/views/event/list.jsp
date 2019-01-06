<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h4 class="header">Events</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
			<th>car(car_id)</th>
			<th>place(place_id)</th>
			<th>time of start</th>
			<th>time of end</th>
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="event" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${event.id}" /></td>

				<td><c:out value="${event.carNumber}(carId=${event.carId})" /></td>

				<td><c:out value="${event.placeName}(placeId=${event.placeId})" /></td>


				<td><c:out value="${event.timeStart}" /></td>
				<td><c:out value="${event.timeEnd}" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${event.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${event.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesEvent}/${event.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesEvent}/${event.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesEvent}/${event.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${pagesEvent}/add"><i
	class="material-icons">add</i></a>
