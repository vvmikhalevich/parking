<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h4 class="header">Clients</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
			<th>user(user_account_id)</th>
			<th>car(number)</th>
			<th>tariff(tariff_id)</th>
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="client" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${client.id}" /></td>

				<td><c:out
						value="${client.userAccountLastName}(userAccountId=${client.userAccountId})" /></td>

				<td><c:out
						value="${client.carModelName}(number=${client.carNumber})" /></td>
				<td><c:out
						value="${client.tariffName}(tariffId=${client.tariffId})" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${client.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${client.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesClient}/${client.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesClient}/${client.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesClient}/${client.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${pagesClient}/add"><i
	class="material-icons">add</i></a>
