<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">
	<c:choose>
		<c:when test="${empty formModel.id }">Create user account</c:when>
		<c:otherwise>Edit user account</c:otherwise>
	</c:choose>
</h4>
<div class="row">
	<div class="row">
		<form:form class="col s12" method="POST" action="${pagesUserAccount}"
			modelAttribute="formModel">

			<form:input path="id" type="hidden" />


			<div class="row">
				<div class="input-field col s12">
					<form:input path="firstName" type="text" disabled="${readonly}" />
					<form:errors path="firstName" cssClass="red-text" />
					<label for="firstName">First Name</label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<form:input path="lastName" type="text" disabled="${readonly}" />
					<form:errors path="lastName" cssClass="red-text" />
					<label for="lastName">Last Name</label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<form:select path="role" disabled="${readonly}">
						<form:options items="${rolesChoices}" />
					</form:select>
					<form:errors path="role" cssClass="red-text" />
					<label for="role">role</label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<form:input path="email" type="text" disabled="${readonly}" />
					<form:errors path="email" cssClass="red-text" />
					<label for="email">Email</label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<form:input path="password" type="text" disabled="${readonly}" />
					<form:errors path="password" cssClass="red-text" />
					<label for="password">Password</label>
				</div>
			</div>
			<div class="row">
				<div class="col s6"></div>
				<div class="col s3">
					<c:if test="${!readonly}">
						<button class="btn waves-effect waves-light right" type="submit">сохранить</button>
					</c:if>
				</div>
				<div class="col s3">
					<a class="btn waves-effect waves-light right"
						href="${pagesUserAccount}">к списку<i
						class="material-icons right"></i>
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