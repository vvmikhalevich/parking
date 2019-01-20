<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">
	<c:choose>
		<c:when test="${empty formModel.id }">Create tariff</c:when>
		<c:otherwise>Edit tariff</c:otherwise>
	</c:choose>
</h4>

<div class="row">
	<form:form class="col s12" method="POST" action="${pagesTariff}"
		modelAttribute="formModel">

		<form:input path="id" type="hidden" />


		<div class="row">
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name">название</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:input path="costPerDay" type="text" disabled="${readonly}" />
				<form:errors path="costPerDay" cssClass="red-text" />
				<label for="costPerDay">costPerDay</label>
			</div>
		</div>


		<div class="row">
			<div class="input-field col s12">
				<form:select path="status" disabled="${readonly}">
					<form:options items="${statusesChoices}" />
				</form:select>
				<form:errors path="status" cssClass="red-text" />
				<label for="status">status</label>
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
				<a class="btn waves-effect waves-light right" href="${pagesTariff}">к
					списку<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>


<
