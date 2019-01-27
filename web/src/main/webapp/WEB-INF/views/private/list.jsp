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
					<a class="left-menu-href" href="${pagesPrivate}">Профиль</a>
				</div>

				<br />
				<div class="menuitem">
					<a class="" href="${pagesPrivate}/transaction/${userAccountId}">История
						расходов</a>
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

					<th>Your account</th>

					<tbody>
						<tr>
							<th>first name</th>
							<th>last name</th>
							<th>role</th>
							<th>email</th>

							<th><mytaglib:i18n key="table.column.created" /></th>
							<th><mytaglib:i18n key="table.column.updated" /></th>
							<th></th>
						</tr>
						<c:forEach var="userAccount" items="${gridItems}"
							varStatus="loopCounter">
							<tr>

								<td><c:out
										value="${userAccount.firstName}(${userAccount.id})" /></td>
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


					<th>Your car(s)</th>

					<tbody>
						<tr>

							<th>model</th>
							<th>number</th>
							<th>userAccount</th>
							<th>tariff</th>
							<th>foto</th>

							<th><mytaglib:i18n key="table.column.created" /></th>
							<th><mytaglib:i18n key="table.column.updated" /></th>
							<th></th>
						</tr>
						<c:forEach var="car" items="${gridItems2}" varStatus="loopCounter">
							<tr>
								<td><a href="${pagesModel}/${car.modelId}">${car.modelName}</a></td>

								<td><c:out value="${car.number}" /></td>
								<td><a href="${pagesUserAccount}/${car.userAccountId}">${car.userAccountLastName}</a></td>
								<td><a href="${pagesTariff}/${car.tariffId}">${car.tariffName}</a></td>
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

					<th>Your balance</th>
					<tbody>


						<td>Your balance</td>
						<td>${balance}</td>

					</tbody>

				</table>

				
			</div>
		</div>




	</div>

</body>

