<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h4 class="header" align="center">Private</h4>


<body>

	<div class="row">


		<div class="col col-sm-3">

			<div class="leftmenu hidden-xs">
				<div class="selecteditem">
					<a class="left-menu-href" href="${pagesPrivate}"><mytaglib:i18n
							key="table.column.Private" /></a>
				</div>

				<br />
				<div class="menuitem">
					<a class="" href="${pagesPrivate}/transaction/${userAccountId}"><mytaglib:i18n
							key="table.column.HistoryBalance" /></a>
				</div>

				<br />
				<%-- <div class="menuitem">
					<a class="" href="${pagesPrivate}/event/${userAccountId}">История
						событий</a>
				</div> --%>
			</div>

		</div>


		<div class="col col-sm-9">
			<div class="row">

				<table>

					<th><mytaglib:i18n key="table.column.YourAccount"/></th>

					<tbody>
						<tr>
							<th><mytaglib:i18n key="table.column.FirstName" /></th>
							<th><mytaglib:i18n key="table.column.LastName" /></th>
							<th><mytaglib:i18n key="table.column.Role" /></th>
							<th>Email</th>

							<th><mytaglib:i18n key="table.column.created" /></th>
							<th><mytaglib:i18n key="table.column.updated" /></th>
							<th></th>
						</tr>
						<c:forEach var="userAccount" items="${gridItems}"
							varStatus="loopCounter">
							<tr>

								<td><c:out
										value="${userAccount.firstName}" /></td>
								<td><c:out value="${userAccount.lastName}" /></td>
								<td><c:out value="${userAccount.role}" /></td>
								<td><c:out value="${userAccount.email}" /></td>

								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${userAccount.created}" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${userAccount.updated}" /></td>

							</tr>
						</c:forEach>
					</tbody>


					<th><mytaglib:i18n key="table.column.YourCars" /></th>

					<tbody>
						<tr>

							<th><mytaglib:i18n key="table.column.Model" /></th>
							<th><mytaglib:i18n key="table.column.Number" /></th>
							<th><mytaglib:i18n key="table.column.UserAccount" /></th>
							<th><mytaglib:i18n key="table.column.Tariff" /></th>
							<th><mytaglib:i18n key="table.column.Foto" /></th>

							<th><mytaglib:i18n key="table.column.created" /></th>
							<th><mytaglib:i18n key="table.column.updated" /></th>
							<th></th>
						</tr>
						<c:forEach var="car" items="${gridItems2}" varStatus="loopCounter">
							<tr>
								<td>${car.modelName}</a></td>

								<td><c:out value="${car.number}" /></td>
								<td>${car.userAccountLastName}</a></td>
								<td>${car.tariffName}</a></td>
								<td><img alt="no image"
									src="${contextPath}/file/image?uuid=${car.link}" width="120"
									height="100" /></td>

								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${car.created}" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${car.updated}" /></td>

							</tr>
						</c:forEach>
					</tbody>

					<th><mytaglib:i18n key="table.column.YourBalance"/></th>
					<tbody>


						<td><mytaglib:i18n key="table.column.YourBalance"/></td>
						<td> ${balance} BYN</td>

					</tbody>

				</table>


			</div>
		</div>




	</div>

</body>

