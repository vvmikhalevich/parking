<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<h4 class="header">
	<c:choose>
		<c:when test="${empty formModel.id }">
			<mytaglib:i18n key="table.column.create" />
			<mytaglib:i18n key="table.column.car" />
		</c:when>
		<c:when test="${readonly}">
			<mytaglib:i18n key="table.column.info" />
			<mytaglib:i18n key="table.column.about" />
			<mytaglib:i18n key="table.column.car" />
		</c:when>
		<c:when test="${!readonly}">
			<mytaglib:i18n key="table.column.edit" />
			<mytaglib:i18n key="table.column.car" />
		</c:when>
	</c:choose>
</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesCar}/add"
		enctype="multipart/form-data" modelAttribute="formModel">

		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:select path="modelId" disabled="${readonly}">
					<form:options items="${modelsChoices}" />
				</form:select>
				<form:errors path="modelId" cssClass="red-text" />
				<label for="modelId"><mytaglib:i18n key="table.column.Model" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="number" type="text" disabled="${readonly}" />
				<form:errors path="number" cssClass="red-text" />
				<label for="number"><mytaglib:i18n key="table.column.Number" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="userAccountId" disabled="${readonly}">
					<form:options items="${userAccountsChoices}" />
				</form:select>
				<form:errors path="userAccountId" cssClass="red-text" />
				<label for="userAccountId"><mytaglib:i18n
						key="table.column.UserAccount" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="tariffId" disabled="${readonly}">
					<form:options items="${tariffsChoices}" />
				</form:select>
				<form:errors path="tariffId" cssClass="red-text" />
				<label for="tariffId"><mytaglib:i18n
						key="table.column.Tariff" /></label>
			</div>
		</div>
		<label for="foto"><mytaglib:i18n key="table.column.Foto" /></label>
		<c:if test="${!readonly}">
			<div class="row">

				<div class="input-field col s12">
					<input type="file" name="file" /> <label for="file"></label>
				</div>
			</div>

		</c:if>

		<c:if test="${!empty formModel.id }">
			<img alt="no image"
				src="${contextPath}/file/image?uuid=${formModel.link}" width="120"
				height="100" />
			<div class="input-field col s12" visibility: hidden>
				<form:input path="link" type="text" disabled="${readonly}" />
				<form:errors path="link" cssClass="red-text" />
				<label for="link">Link</label>
			</div>
			<div class="input-field col s12" visibility: hidden>
				<form:input path="fotoId" type="text" disabled="${readonly}" />
				<form:errors path="fotoId" cssClass="red-text" />
				<label for="fotoId">Foto id</label>
			</div>

		</c:if>

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
				<a class="btn waves-effect waves-light right" href="${pagesCar}"><mytaglib:i18n
						key="table.column.cancel" /><i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>



