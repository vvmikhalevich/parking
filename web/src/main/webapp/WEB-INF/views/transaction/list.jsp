<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h4 class="header">Transactions</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
			<th>client(client_id)</th>
			<th>value</th>
			<th>description</th>
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="transaction" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${transaction.id}" /></td>

				<td><c:out
						value="${transaction.clientId}(client_id=${transaction.clientId})" /></td>
				<td><c:out value="${transaction.value}" /></td>
				<td><c:out value="${transaction.description}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${transaction.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${transaction.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesTransaction}/${transaction.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="${pagesTransaction}/${transaction.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesTransaction}/${transaction.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right"
	href="${pagesTransaction}/add"><i class="material-icons">add</i></a>
