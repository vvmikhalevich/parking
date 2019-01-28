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
					<th><mytaglib:i18n key="table.column.YourBalance" /></th>
					<tbody>


						<td><mytaglib:i18n key="table.column.YourBalance" /></td>
						<td>${balance}</td>

					</tbody>

					<th><mytaglib:i18n key="table.column.YourTransaction" /></th>

					<tbody>
						<tr>
							<th><mytaglib:i18n key="table.column.id" /></th>
							<th><mytaglib:i18n key="table.column.UserAccount" /></th>
							<th><mytaglib:i18n key="table.column.Value" /></th>
							<th><mytaglib:i18n key="table.column.Description" /></th>
							<th><mytaglib:i18n key="table.column.created" /></th>
							<th><mytaglib:i18n key="table.column.updated" /></th>
							<th></th>
						</tr>
						<c:forEach var="transaction" items="${gridItems}"
							varStatus="loopCounter">
							<tr>
								<td><c:out value="${transaction.id}" /></td>

								<td>${transaction.userAccountLastName}</a></td>
								<td><c:out value="${transaction.value}" /></td>
								<td><c:out value="${transaction.description}" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${transaction.created}" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${transaction.updated}" /></td>

							</tr>
						</c:forEach>
					</tbody>




				</table>


			</div>
		</div>




	</div>

</body>

