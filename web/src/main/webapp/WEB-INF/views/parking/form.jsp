<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">
	<c:choose>
		<c:when test="${empty formModel.id }">Create parking</c:when>
		<c:otherwise>Edit parking</c:otherwise>
	</c:choose>
</h4>
<div class="row">
	<div class="row">
		<form:form class="col s12" method="POST" action="${pagesParking}"
			modelAttribute="formModel">

			<form:input path="id" type="hidden" />


			<div class="row">
				<div class="input-field col s12">
					<form:input path="name" type="text" disabled="${readonly}" />
					<form:errors path="name" cssClass="red-text" />
					<label for="name">Name of parking</label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<form:input path="adress" type="text" disabled="${readonly}" />
					<form:errors path="adress" cssClass="red-text" />
					<label for="adress">Adress</label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<form:input path="status" type="text" disabled="${readonly}" />
					<form:errors path="status" cssClass="red-text" />
					<label for="status">Status</label>
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
						href="${pagesParking}">к списку<i
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