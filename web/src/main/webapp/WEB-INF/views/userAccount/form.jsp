<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<h4 class="header">
	<c:choose>
		<c:when test="${empty formModel.id }">
			<mytaglib:i18n key="table.column.create" />
			<mytaglib:i18n key="table.column.userAccounta" />
		</c:when>
		<c:when test="${readonly}">
			<mytaglib:i18n key="table.column.info" />
			<mytaglib:i18n key="table.column.about" />
			<mytaglib:i18n key="table.column.userAccounte" />
		</c:when>
		<c:when test="${!readonly}">
			<mytaglib:i18n key="table.column.edit" />
			<mytaglib:i18n key="table.column.userAccounta" />
		</c:when>
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
					<label for="firstName"><mytaglib:i18n
							key="table.column.FirstName" /></label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<form:input path="lastName" type="text" disabled="${readonly}" />
					<form:errors path="lastName" cssClass="red-text" />
					<label for="lastName"><mytaglib:i18n
							key="table.column.LastName" /></label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<form:select path="role" disabled="${readonly}">
						<form:options items="${rolesChoices}" />
					</form:select>
					<form:errors path="role" cssClass="red-text" />
					<label for="role"><mytaglib:i18n key="table.column.Role" /></label>
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
					<label for="password"><mytaglib:i18n
							key="table.column.Password" /></label>
				</div>
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
					<a class="btn waves-effect waves-light right" href="${pagesUserAccount}"><mytaglib:i18n
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