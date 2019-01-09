<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">
	<c:choose>
		<c:when test="${empty formModel.id }">Create event</c:when>
		<c:otherwise>Edit event</c:otherwise>
	</c:choose>
</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesEvent}"
		modelAttribute="formModel">

		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:select path="carId" disabled="${readonly}">
					<form:options items="${carsChoices}" />
				</form:select>
				<form:errors path="carId" cssClass="red-text" />
				<label for="carId">Car</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="placeId" disabled="${readonly}">
					<form:options items="${placesChoices}" />
				</form:select>
				<form:errors path="placeId" cssClass="red-text" />
				<label for="placeId">Place</label>
			</div>
		</div>




		<div class="row">
			<div class="input-field col s12">
				<form:input path="timeStart" type="text" disabled="${readonly}" />
				<form:errors path="timeStart" cssClass="red-text" />
				<label for="timeStart">Time of start (year-month-day)</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="timeEnd" type="text" disabled="${readonly}" />
				<form:errors path="timeEnd" cssClass="red-text" />
				<label for="timeEnd">Time of end (year-month-day)</label>
			</div>
		</div>


		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">Save</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${pagesEvent}">to
					the listƒ<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>


<c:if test='${param["showAlerts"]}'>
	<!-- checks the URL parameter -->


	<script src="${contextPath}/resources/js/sample-alert-with-params.js"></script>
	<script>
		showMessage('${contextPath}'); // execute function defined somewhere above
	</script>

</c:if>

