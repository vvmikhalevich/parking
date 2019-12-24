<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<h4 class="header">
	<c:choose>
		<c:when test="${empty formModel.id }">
			<mytaglib:i18n key="table.column.create" />
			<mytaglib:i18n key="table.column.event" />

		</c:when>
		<c:when test="${readonly}">
			<mytaglib:i18n key="table.column.info" />
			<mytaglib:i18n key="table.column.about" />
			<mytaglib:i18n key="table.column.event" />
		</c:when>
		<c:when test="${!readonly}">
			<mytaglib:i18n key="table.column.edit" />
			<mytaglib:i18n key="table.column.event" />
		</c:when>
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
				<label for="carId"><mytaglib:i18n key="table.column.Car" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="placeId" disabled="${readonly}">
					<form:options items="${placesChoices}" />
				</form:select>
				<form:errors path="placeId" cssClass="red-text" />
				<label for="placeId"><mytaglib:i18n key="table.column.Place" /></label>
			</div>
		</div>




		<div class="row">
			<div class="input-field col s6">
				<form:input path="timeStart" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="timeStart" cssClass="red-text" />
				<label for="timeStart"><mytaglib:i18n
						key="table.column.DateOfStart" /></label>
			</div>
			<div class="input-field col s6">
				<form:input path="timeStartTime" type="text" disabled="${readonly}"
					cssClass="timepicker" />
				<form:errors path="timeStartTime" cssClass="red-text" />
				<label for="timeStartTime"><mytaglib:i18n
						key="table.column.TimeOfStart" /></label>
			</div>


			<div class="row">
				<div class="input-field col s6">
					<form:input path="timeEnd" type="text" disabled="${readonly}"
						cssClass="datepicker" />
					<form:errors path="timeEnd" cssClass="red-text" />
					<label for="timeEnd"><mytaglib:i18n
							key="table.column.DateOfEnd" /></label>
				</div>
				<div class="input-field col s6">
					<form:input path="timeEndTime" type="text" disabled="${readonly}"
						cssClass="timepicker" />
					<form:errors path="timeEndTime" cssClass="red-text" />
					<label for="timeEndTime"><mytaglib:i18n
							key="table.column.TimeOfEnd" /></label>
				</div>



				<div class="row">
					<div class="col s6"></div>
					<div class="col s3">
						<c:if test="${!readonly}">
							<button class="btn waves-effect waves-light right" type="submit">
								<mytaglib:i18n key="table.column.save" />
							</button>
						</c:if>
					</div>
					<div class="col s3">
						<a class="btn waves-effect waves-light right" href="${pagesEvent}"><mytaglib:i18n
								key="table.column.cancel" /><i class="material-icons right"></i>
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

