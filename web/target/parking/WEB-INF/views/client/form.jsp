<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">
	<c:choose>
		<c:when test="${empty formModel.id }">Create client</c:when>
		<c:otherwise>Edit client</c:otherwise>
	</c:choose>
</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesClient}"
		modelAttribute="formModel">

		<form:input path="id" type="hidden" />

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
				<form:select path="carId" disabled="${readonly}">
					<form:options items="${carsChoices}" />
				</form:select>
				<form:errors path="carId" cssClass="red-text" />
				<label for="carId">Cars</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="tariffId" disabled="${readonly}">
					<form:options items="${tariffsChoices}" />
				</form:select>
				<form:errors path="tariffId" cssClass="red-text" />
				<label for="tariffId">Tariffs</label>
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
				<a class="btn waves-effect waves-light right" href="${pagesClient}">к
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

