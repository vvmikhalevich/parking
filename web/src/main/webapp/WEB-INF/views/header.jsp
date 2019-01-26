<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<header>

	<ul id="dropdown1" class="dropdown-content">
		<li><a href="${pagesBrand}"><mytaglib:i18n
					key="table.column.brands" /></a></li>
		<li><a href="${pagesModel}"><mytaglib:i18n
					key="table.column.models" /></a></li>
		<li class="divider"></li>
		<li><a href="${pagesCar}">Cars</a></li>
	</ul>

	<ul id="dropdown2" class="dropdown-content">
		<li><a href="${pagesParking}">Parkings</a></li>
		<li><a href="${pagesPlace}">Places</a></li>
		<li class="divider"></li>
		<li><a href="${pagesTariff}">Tariff</a></li>
	</ul>

	<nav class="nav-extended">

		<div class="nav-wrapper container">


			<ul class="left hide-on-med-and-down">


				<li><a href="${contextPath}/">Home</a></li>

				<li><sec:authorize access="hasAnyRole('admin', 'manager')">
						<li><a class="dropdown-trigger" href="#!"
							data-target="dropdown1">Cars<i class="material-icons right">arrow_drop_down</i></a></li>
					</sec:authorize></li>

				<li><a class="dropdown-trigger" href="#!"
					data-target="dropdown2">Parkings<i class="material-icons right">arrow_drop_down</i></a></li>

				<sec:authorize access="!hasAnyRole('admin', 'manager')">

					<li><a href="${pagesPrivate}">Private</a></li>
				</sec:authorize>


				<li><sec:authorize access="hasAnyRole('admin', 'manager')">
						<li><a href="${pagesUserAccount}">UserAccounts</a></li>
					</sec:authorize> <%-- <li><sec:authorize access="hasAnyRole('admin', 'manager')">
						<li><a href="${pagesFoto}">Photos</a></li>
					</sec:authorize></li> --%>
				<li><sec:authorize access="hasAnyRole('admin', 'manager')">
						<li><a href="${pagesEvent}">Events</a></li>
					</sec:authorize></li>

				<li><sec:authorize access="hasAnyRole('admin', 'manager')">
						<a href="${pagesTransaction}">Transactions</a>
					</sec:authorize></li>


				<sec:authorize access="!isAnonymous()">
					<sec:authentication property="name" />
					<a class="right" href="${contextPath}/execute_logout"
						title="logout"><i class="material-icons">arrow_forward</i></a>
				</sec:authorize>



			</ul>
			<ul class="right hide-on-med-and-down">
				<li><a class="right" class="white-text"
					href="${contextPath}?language=ru">RU</a></li>
				<li><a class="white-text" href="${contextPath}?language=en">EN</a></li>
				<sec:authorize access="!isAuthenticated()">
					<li><a href="${contextPath}/login">Login</a></li>
				</sec:authorize>
			</ul>

		</div>


	</nav>
	<ul id="dropdown1" class="dropdown-content">
		<li><a href="#!">one</a></li>
		<li><a href="#!">two</a></li>
		<li class="divider"></li>
		<li><a href="#!">three</a></li>
	</ul>
</header>
<script>
$(document).ready(function){
	$(".dropdown-trigger").dropdown();
}
</script>