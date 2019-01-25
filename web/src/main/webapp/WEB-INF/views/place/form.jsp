<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">
	<c:choose>
		<c:when test="${empty formModel.id }">Create place</c:when>
		<c:otherwise>Edit place</c:otherwise>
	</c:choose>
</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesPlace}/add"
		modelAttribute="formModel">

		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name">Name of place</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="parkingId" disabled="${readonly}">
					<form:options items="${parkingsChoices}" />
				</form:select>
				<form:errors path="parkingId" cssClass="red-text" />
				<label for="parkingId">Parking</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="carId" disabled="${readonly}">
					<form:option value="">empty</form:option>
					<form:options items="${carsChoices}" />
				</form:select>
				<form:errors path="carId" cssClass="red-text" />
				<label for="carId">Car</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="userAccountId" disabled="${readonly}">
					<form:option value="">empty</form:option>
					<form:options items="${usersChoices}" />
				</form:select>
				<form:errors path="userAccountId" cssClass="red-text" />
				<label for="userAccountId">User</label>
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
				<a class="btn waves-effect waves-light right" href="${pagesPlace}">to
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

