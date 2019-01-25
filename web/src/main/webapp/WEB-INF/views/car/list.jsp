<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h4 class="header">Cars</h4>


<div class="row">
	<div class="col s12 m12">
		<div class="card-panel blue lighten-5">
			<div class="row">
				<form:form method="POST" action="${pagesCar}"
					enctype="multipart/form-data" modelAttribute="searchFormModel">
					<div class="input-field col s4">
						<form:input path="number" type="text" />
						<label for="number">Number</label>
					</div>
					<div class="col s4">
						<button class="btn waves-effect waves-light right" type="submit">search</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesCar}" column="id">
					<mytaglib:i18n key="table.column.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCar}" column="model">model</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCar}" column="number">number</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCar}"
					column="userAccount">userAccount</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCar}" column="tariff">tariff</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCar}" column="foto">foto</mytaglib:sort-link></th>
			<th>Select car of place</th>
			<th><mytaglib:sort-link pageUrl="${pagesCar}" column="created">
					<mytaglib:i18n key="table.column.created" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCar}" column="updated">
					<mytaglib:i18n key="table.column.updated" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="car" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${car.id}" /></td>

				<td><a href="${pagesModel}/${car.modelId}">${car.modelName}</a></td>

				<td><c:out value="${car.number}" /></td>
				<td><a href="${pagesUserAccount}/${car.userAccountId}">${car.userAccountLastName}</a></td>
				<td><a href="${pagesTariff}/${car.tariffId}">${car.tariffName}</a></td>
				<td><img alt="no image"
					src="${contextPath}/file/image?uuid=${car.link}" width="120"
					height="100" /></td>

				<td><a class="waves-effect waves-light btn-small"
					href="${pagesCar}/${car.id}/put"><i class="material-icons">check</i>put</a></td>

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
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesCar}/add"><i
	class="material-icons">add</i></a>
