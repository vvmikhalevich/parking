<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Places</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesPlace}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPlace}" column="name">name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPlace}" column="parking">parking</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPlace}" column="car">car</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPlace}"
					column="userAccount">userAccount</mytaglib:sort-link></th>

			<th><mytaglib:sort-link pageUrl="${pagesPlace}" column="created">
					<mytaglib:i18n key="table.column.created" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPlace}" column="updated">
					<mytaglib:i18n key="table.column.updated" />
				</mytaglib:sort-link></th>
			<th>Out from place</th>
			<th></th>
		</tr>
		<c:forEach var="place" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td>${place.id}</td>

				<td>${place.name}</td>

				<td><a href="${pagesParking}/${place.parkingId}">${place.parkingName}</a></td>

				<td><a href="${pagesCar}/${place.carId}">${place.carNumber}</a></td>

				<td><a href="${pagesUserAccount}/${place.userAccountId}">${place.userAccountLastName}</a></td>


				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${place.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${place.updated}" /></td>
				<td><c:if test="${place.carId!='0'}">
						<a class="btn-floating btn-small waves-effect waves-light red"
							href="${pagesPlace}/${place.id}/deleteCar"><i
							class="material-icons">delete car from place</i></a>
					</c:if> <c:if test="${place.carId=='0'}">
						<a
							class="btn-floating btn-small waves-effect waves-light red disabled"
							href="${pagesPlace}/${place.id}/deleteCar"><i
							class="material-icons">delete car from place</i></a>
					</c:if></td>


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
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesPlace}/add"><i
	class="material-icons">add</i></a>
