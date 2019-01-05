<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<header>
	<nav class="nav-extended">
		<div class="nav-wrapper ">
			<ul class="left hide-on-med-and-down">
				<li><a href="${contextPath}/">Home</a></li>
				<li><a href="${pagesBrand}">Brands</a></li>
				<li><a href="${pagesModel}">Models</a></li>
				<li><a href="${pagesCar}">Cars</a></li>
				<li><a href="${pagesClient}">Clients</a></li>
				<li><a href="${pagesTariff}">Tariffs</a></li>
				<li><a href="${pagesUserAccount}">UserAccounts</a></li>
				<li><a href="${pagesFoto}">Photos</a></li>
				<li><a href="${pagesParking}">Parkings</a></li>
				<li><a href="${pagesPlace}">Places</a></li>

				<li><a href="${pagesPlaceOwner}">PlaceOwners</a></li>
				<li><a href="${pagesEvent}">Events</a></li>
				<li><a href="${pagesTransaction}">Transactions</a></li>


				<sec:authorize access="!isAnonymous()">
					<a class="right" href="${contextPath}/execute_logout"
						title="logout"><i class="material-icons">arrow_forward</i></a>
				</sec:authorize>


			</ul>
		</div>

	</nav>
</header>