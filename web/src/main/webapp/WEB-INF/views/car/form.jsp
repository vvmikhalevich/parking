<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">
	<c:choose>
		<c:when test="${empty formModel.id }">Create car</c:when>
		<c:otherwise>Edit car</c:otherwise>
	</c:choose>
</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesCar}"
		enctype="multipart/form-data" modelAttribute="formModel">

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
				<label for="userAccountId">User of car</label>
			</div>
		</div>

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
			<div class="input-field col s12">
				<form:input path="link" type="text" disabled="${readonly}" />
				<form:errors path="link" cssClass="red-text" />
				<label for="link">Link</label>
			</div>
			<div class="input-field col s12">
				<form:input path="fotoId" type="text" disabled="${readonly}" />
				<form:errors path="fotoId" cssClass="red-text" />
				<label for="fotoId">Foto id</label>
			</div>

		</c:if>

		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">save</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${pagesCar}">cancel<i
					class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>



