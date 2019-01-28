<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<h4 class="header">
	<c:choose>
		<c:when test="${empty formModel.id }">
			<mytaglib:i18n key="table.column.create" />
			<mytaglib:i18n key="table.column.parking" />
		</c:when>
		<c:otherwise>
			<entry key="table.column.edit">Edit</entry>
			<mytaglib:i18n key="table.column.parking" />
		</c:otherwise>

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
					<label for="name"><mytaglib:i18n key="table.column.name" /></label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<form:input path="adress" type="text" disabled="${readonly}" />
					<form:errors path="adress" cssClass="red-text" />
					<label for="adress"><mytaglib:i18n
							key="table.column.Adress" /></label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<form:input path="width" type="text" disabled="${readonly}" />
					<form:errors path="width" cssClass="red-text" />
					<label for="width"><mytaglib:i18n key="table.column.Width" /></label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<form:input path="length" type="text" disabled="${readonly}" />
					<form:errors path="length" cssClass="red-text" />
					<label for="length"><mytaglib:i18n
							key="table.column.Length" /></label>
				</div>
			</div>

			<div class="row">
				<div class="input-field col s12">
					<form:select path="status" disabled="${readonly}">
						<form:options items="${statusesChoices}" />
					</form:select>
					<form:errors path="status" cssClass="red-text" />
					<label for="status"><mytaglib:i18n
							key="table.column.Status" /></label>
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
					<a class="btn waves-effect waves-light right" href="${pagesParking}"><mytaglib:i18n
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