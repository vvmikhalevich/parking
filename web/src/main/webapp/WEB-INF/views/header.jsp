<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<header>
	<nav class="nav-extended">
		<div class="nav-wrapper">
			<ul class="right hide-on-med-and-down">
				<li><a class="white-text" href="${contextPath}?language=ru">RU</a></li>
				<li><a class="white-text" href="${contextPath}?language=en">EN</a></li>
			</ul>

		</div>
		<div class="nav-wrapper container">
			<ul class="left hide-on-med-and-down">
				<li><a href="${contextPath}/">Home</a></li>
				<li><a href="${pagesBrand}"><mytaglib:i18n
							key="table.column.brands" /></a></li>
				<li><a href="${pagesModel}"><mytaglib:i18n
							key="table.column.models" /></a></li>
				<li><a href="${pagesCar}">Cars</a></li>


				<li><a href="${pagesUserAccount}">UserAccounts</a></li>
				<li><a href="${pagesFoto}">Photos</a></li>
				<li><a href="${pagesParking}">Parkings</a></li>
				<li><a href="${pagesPlace}">Places</a></li>


				<li><a href="${pagesEvent}">Events</a></li>

				<li><sec:authorize access="!isAnonymous()">
						<a href="${pagesTransaction}">Transactions</a>
					</sec:authorize></li>


				<sec:authorize access="!isAnonymous()">
					<a class="right" href="${contextPath}/execute_logout"
						title="logout"><i class="material-icons">arrow_forward</i></a>
				</sec:authorize>





			</ul>

		</div>

	</nav>
</header>