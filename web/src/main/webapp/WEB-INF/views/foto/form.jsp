<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">
	<c:choose>
		<c:when test="${empty formModel.id }">Create foto</c:when>
		<c:otherwise>Edit foto</c:otherwise>
	</c:choose>
</h4>
<div class="row">
	<div class="row">
		<form:form class="col s12" method="POST" action="${pagesFoto}"
			modelAttribute="formModel">

			<form:input path="id" type="hidden" />


			<div class="row">
				<div class="input-field col s12">
					<form:input path="link" type="text" disabled="${readonly}" />
					<form:errors path="link" cssClass="red-text" />
					<label for="link">название</label>
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
					<a class="btn waves-effect waves-light right" href="${pagesFoto}">к
						списку<i class="material-icons right"></i>
					</a>
				</div>
			</div>
		</form:form>
	</div>

	<div>
		<form method="POST" enctype="multipart/form-data"
			action="${contextPath}/file">
			<table>
				<tr>
					<td>File to upload:</td>
					<td><input type="file" name="file" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Upload" /></td>
				</tr>
			</table>
		</form>

		<img alt="no image"
			src="${contextPath}/file/image?uuid=<%= request.getParameter("uuid") %>"
			width="180" height="150" />
	</div>


	<c:if test='${param["showAlerts"]}'>
		<!-- checks the URL parameter -->


		<script src="${contextPath}/resources/js/sample-alert-with-params.js"></script>
		<script>
			showMessage('${contextPath}'); // execute function defined somewhere above
		</script>

	</c:if>