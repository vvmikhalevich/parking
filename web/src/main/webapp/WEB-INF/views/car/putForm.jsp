<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Put the car</h4>
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
				<label for="userAccountId">User of car</label>
			</div>
		</div>





		<div class="row">
			<div class="input-field col s12">
				<form:input path="number" type="text" disabled="true" />
				<form:errors path="number" cssClass="red-text" />
				<label for="number">Number of car</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="placeId" disabled="${readonly}">
					<form:options items="${placesChoices}" />
				</form:select>
				<form:errors path="placeId" cssClass="red-text" />
				<label for="placeId">Place</label>
			</div>
		</div>




		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${placesChoices!='{}'}">
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



