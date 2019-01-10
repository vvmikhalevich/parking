<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">
	<c:choose>
		<c:when test="${empty formModel.id }">Create places</c:when>
		<c:otherwise>Edit parking</c:otherwise>
	</c:choose>
</h4>
<div class="row">
	<div class="row">
		<form:form class="col s12" method="POST" action="${pagesParking}"
			modelAttribute="formModel">

			<form:input path="id" type="hidden" />

			<html>

<head>
<meta charset="utf-8">
</head>
<body>

	<table border="1" cellspacing="1" width="100%" id="table1">
		<tr>
			<th>Column1</th>
			<th>Column2</th>
			<th>Column3</th>
			<th>Column4</th>
			<th>Column5</th>
		</tr>
		<tr>
			<td>place</td>
			<td>place</td>
			<td>place</td>
			<td>place</td>
			<td>place</td>
		</tr>
		<tr>
			<td>place</td>
			<td>place</td>
			<td>place</td>
			<td>place</td>
			<td>place</td>
		</tr>
		<tr>
			<td>place</td>
			<td>place</td>
			<td>place</td>
			<td>place</td>
			<td>place</td>
		</tr>
		<tr>
			<td>place</td>
			<td>place</td>
			<td>place</td>
			<td>place</td>
			<td>place</td>
		</tr>
	</table>

</body>

<script>
	$(document).ready(
			function() {
				$('td').click(
						function() {
							if (this.style.background == ""
									|| this.style.background == "white") {
								$(this).css('background', 'red');
							} else {
								$(this).css('background', 'white');
							}
						});
			});
</script>

			</html>



		</form:form>

		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">Save
						places</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${pagesParking}">to
					parkings<i class="material-icons right"></i>
				</a>
			</div>
		</div>

	</div>




	<c:if test='${param["showAlerts"]}'>
		<!-- checks the URL parameter -->


		<script src="${contextPath}/resources/js/sample-alert-with-params.js"></script>
		<script>
			showMessage('${contextPath}'); // execute function defined somewhere above
		</script>

	</c:if>