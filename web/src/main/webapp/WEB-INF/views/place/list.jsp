<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h4 class="header">
	<mytaglib:i18n key="table.column.Places" />
</h4>
<div class="row">
	<div class="col s12 m12">
		<div class="card-panel blue lighten-5">
			<div class="row">
				<form:form method="POST" action="${pagesPlace}"
					enctype="multipart/form-data" modelAttribute="searchFormModel">
					<div class="input-field col s4">
						<form:input path="number" type="text" />
						<label for="number"><mytaglib:i18n
								key="table.column.Number" /></label>
					</div>
					<div class="input-field col s1">
						<mytaglib:i18n key="table.column.orAnd" />
					</div>

					<div class="input-field col s4">
						
						<form:input path="userAccountLastName" type="text" />
						<label for="userAccountLastName"><mytaglib:i18n
								key="table.column.LastName" /></label>
					</div>
					<div class="col s3">
						<button class="btn waves-effect waves-light right" type="submit">
							<mytaglib:i18n key="table.column.SEARCH" />
						</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesPlace}" column="id">
					<mytaglib:i18n key="table.column.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPlace}" column="name">
					<mytaglib:i18n key="table.column.name" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPlace}" column="parking">
					<mytaglib:i18n key="table.column.Parkingg" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPlace}" column="car">
					<mytaglib:i18n key="table.column.Car" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPlace}"
					column="userAccount">
					<mytaglib:i18n key="table.column.UserAccount" />
				</mytaglib:sort-link></th>

			<th><mytaglib:sort-link pageUrl="${pagesPlace}" column="created">
					<mytaglib:i18n key="table.column.created" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPlace}" column="updated">
					<mytaglib:i18n key="table.column.updated" />
				</mytaglib:sort-link></th>
			<th><mytaglib:i18n key="table.column.RemoveCar" /></th>
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
