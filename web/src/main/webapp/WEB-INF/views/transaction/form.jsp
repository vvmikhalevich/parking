<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">
	<c:choose>
		<c:when test="${empty formModel.id }">Create transaction</c:when>
		<c:otherwise>Edit transaction</c:otherwise>
	</c:choose>
</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesTransaction}"
		modelAttribute="formModel">

		<form:input path="id" type="hidden" />




		<div class="row">
			<div class="input-field col s12">
				<form:select path="clientId" disabled="${readonly}">
					<form:options items="${clientsChoices}" />
				</form:select>
				<form:errors path="clientId" cssClass="red-text" />
				<label for="clientId">clients</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="value" type="text" disabled="${readonly}" />
				<form:errors path="value" cssClass="red-text" />
				<label for="value">значение платежа</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="description" type="text" disabled="${readonly}" />
				<form:errors path="description" cssClass="red-text" />
				<label for="description">примечание</label>
			</div>
		</div>

		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">ÑÐ¾ÑÑÐ°Ð½Ð¸ÑÑÂ</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right"
					href="${pagesTransaction}">Ðº ÑÐ¿Ð¸ÑÐºÑ<i
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

