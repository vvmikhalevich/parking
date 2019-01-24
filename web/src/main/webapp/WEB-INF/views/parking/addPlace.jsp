<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h4 class="header">
	<c:choose>
		<c:when test="${empty formModel.id }">Create places</c:when>
		<c:when test="${readonly}">Info parking ${formModel.name}</c:when>
		<c:otherwise>Edit parking ${formModel.name}</c:otherwise>

	</c:choose>
</h4>

<table border="1" cellspacing="1"  id="table1"
	class="parking-table">


	<c:forEach var="y" begin="1" end="${formModel.width}">
		<tr>
			<c:forEach var="x" begin="1" end="${formModel.length}">
				<c:set var="cellId" value="${x}_${y}"></c:set>
				
				<c:if test="${readonly}">
				
				<spring:eval var="containsValue" expression="places.contains(cellId)" />
				
				<c:set var="clazz" value="${containsValue  ? 'selected' : ''}"></c:set> 
				</c:if>
				
				<td id="${cellId}" class="${clazz}">${cellId}</td>
			</c:forEach>
		</tr>

	</c:forEach>

</table>

<div class="row">
	<div class="col s6"></div>
	<div class="col s3">
		<c:if test="${!readonly}">
			<button class="btn waves-effect waves-light right" type="button"
				id="save-button">Save places</button>
		</c:if>
	</div>
	<div class="col s3">
		<a class="btn waves-effect waves-light right" href="${pagesParking}">to
			parkings<i class="material-icons right"></i>
		</a>
	</div>
</div>


<c:choose>
	<c:when test="${readonly}">


	</c:when>

	<c:otherwise>
		<script>
			$(document).ready(function() {
				//cell selection
				$('td').click(function() {
					$(this).toggleClass("selected");
				});

				$('#save-button').click(function() {
					var data = {};
					$('.parking-table td').each(function(index) {
						var td = $(this);
						var id = td.attr('id');
						var clazz = td.attr('class');
						data[id] = clazz === 'selected' ? true : false;
					});

					$.ajax({
						type : "POST",
						url : '${pagesParking}/addPlaces/${formModel.id}',
						data : JSON.stringify(data),
						success : function() {
							window.location = '${pagesParking}';
						},
						error : function() {
							alert('unexpected error');
							window.location = '${pagesParking}';
						},
						contentType : "application/json; charset=utf-8",
					});

				});

			});
		</script>
	</c:otherwise>
</c:choose>