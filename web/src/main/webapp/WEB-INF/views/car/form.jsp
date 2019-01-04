<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">
	<c:choose>
		<c:when test="${empty formCar.id }">Create car</c:when>
		<c:otherwise>Edit car</c:otherwise>
	</c:choose>
</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesCar}"
		modelAttribute="formModel">

		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:select path="modelId" disabled="${readonly}">
					<form:options items="${modelsChoices}" />
				</form:select>
				<form:errors path="modelId" cssClass="red-text" />
				<label for="modelId">model</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="number" type="text" disabled="${readonly}" />
				<form:errors path="number" cssClass="red-text" />
				<label for="number">Number of car</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="userAccountId" disabled="${readonly}">
					<form:options items="${userAccountsChoices}" />
				</form:select>
				<form:errors path="userAccountId" cssClass="red-text" />
				<label for="userAccountId">User Account</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="fotoId" disabled="${readonly}">
					<form:options items="${fotosChoices}" />
				</form:select>
				<form:errors path="fotoId" cssClass="red-text" />
				<label for="fotoId">foto of car</label>
			</div>
		</div>

		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">сохранить</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${pagesCar}">к
					списку<i class="material-icons right"></i>
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

