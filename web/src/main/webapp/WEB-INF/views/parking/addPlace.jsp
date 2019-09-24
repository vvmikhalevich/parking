<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">
	<c:choose>
		<c:when test="${empty formModel.id }">
			<mytaglib:i18n key="table.column.create" />
			<mytaglib:i18n key="table.column.places" />
		</c:when>
		<c:when test="${readonly}">
			<mytaglib:i18n key="table.column.info" />
			<mytaglib:i18n key="table.column.about" />
			<mytaglib:i18n key="table.column.parkinga" /> ${formModel.name}</c:when>
		<c:otherwise>
			<mytaglib:i18n key="table.column.edit" />
			<mytaglib:i18n key="table.column.parking" /> ${formModel.name}</c:otherwise>

	</c:choose>
</h4>

<table border="1" cellspacing="1" id="table1" class="parking-table">


	<c:forEach var="y" begin="1" end="${formModel.width}">
		<tr>
			<c:forEach var="x" begin="1" end="${formModel.length}">
				<c:set var="cellId" value="${x}_${y}"></c:set>

				<c:if test="${readonly}">

					<spring:eval var="containsValue"
						expression="places.containsKey(cellId)" />
					<spring:eval var="place" expression="places.get(cellId)" />


					<c:set var="clazz" value="${containsValue  ? 'selected' : ''}"></c:set>
					<c:set var="clazz2"
						value="${containsValue  ? 'unselected' : 'grey'}"></c:set>
				</c:if>

				<td id="${cellId}"
					<c:if test="${cellId!='0' && place.userAccountId!='0' }">
					 
					class="${clazz2}"
					
				</c:if>
					<c:if test="${cellId!='0' && place.userAccountId=='0'}">
					 
					 class="${clazz}"
					
				</c:if>><c:if
						test="${ containsValue}">

						<sec:authorize access="hasAnyRole('admin', 'manager')">
							<mytaglib:i18n key="table.column.place" /> :${place.name} <br>
						</sec:authorize>
						<c:if test="${place.userAccountId!='0'}">
							<mytaglib:i18n key="table.column.full" />
							<c:if test="${place.carId!='0'}">
								<sec:authorize access="hasAnyRole('admin', 'manager')">
									<br>${place.carNumber}
					</sec:authorize>
							</c:if>


						</c:if>
						<c:if test="${place.userAccountId=='0'}">
							<mytaglib:i18n key="table.column.free" />


						</c:if>

					</c:if></td>
			</c:forEach>
		</tr>

	</c:forEach>

</table>

<div class="row">
	<div class="col s6"></div>
	<div class="col s3">
		<sec:authorize access="hasAnyRole('admin')">
			<c:if test="${!readonly}">
				<button class="btn waves-effect waves-light right" type="button"
					id="save-button">
					<mytaglib:i18n key="table.column.save" />
				</button>
			</c:if>
		</sec:authorize>
	</div>
	<div class="col s3">
		<a class="btn waves-effect waves-light right" href="${pagesParking}"><mytaglib:i18n
				key="table.column.cancel" /><i class="material-icons right"></i> </a>
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