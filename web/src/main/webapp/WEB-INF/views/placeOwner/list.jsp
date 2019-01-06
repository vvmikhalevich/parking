<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Places owner</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
			<th>userAccount(user_account_id)</th>
			<th>place(place_id)</th>
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>
		<c:forEach var="placeOwner" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${placeOwner.id}" /></td>

				<td><c:out
						value="${placeOwner.userAccountLastName}(userAccountId=${placeOwner.userAccountId})" /></td>

				<td><c:out
						value="${placeOwner.placeName}(placeId=${placeOwner.placeId})" /></td>

				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${placeOwner.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${placeOwner.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesPlaceOwner}/${placeOwner.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="${pagesPlaceOwner}/${placeOwner.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesPlaceOwner}/${placeOwner.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right"
	href="${pagesPlaceOwner}/add"><i class="material-icons">add</i></a>
