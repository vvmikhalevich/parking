<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<h4 class="header"><mytaglib:i18n key="table.column.PutCarOnPlace" /></h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesCar}/put"
		enctype="multipart/form-data" modelAttribute="formModel">

		<form:input path="id" type="hidden" />
		<form:input path="userAccountId" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:select path="userAccountId" disabled="true">
					<form:options items="${userAccountsChoices}" />
				</form:select>
				<form:errors path="userAccountId" cssClass="red-text" />
				<label for="userAccountId"><mytaglib:i18n
						key="table.column.UserAccount" /></label>
			</div>
		</div>





		<div class="row">
			<div class="input-field col s12">
				<form:input path="number" type="text" disabled="true" />
				<form:errors path="number" cssClass="red-text" />
				<label for="number"><mytaglib:i18n key="table.column.Number" /></label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="placeId" disabled="${readonly}">
					<form:options items="${placesChoices}" />
				</form:select>
				<form:errors path="placeId" cssClass="red-text" />
				<label for="placeId"><mytaglib:i18n
						key="table.column.Place" /></label>
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
				<a class="btn waves-effect waves-light right" href="${pagesCar}"><mytaglib:i18n
						key="table.column.cancel" /><i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>



