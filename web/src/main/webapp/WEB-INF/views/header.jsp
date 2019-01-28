<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<header>

	<ul id="dropdown1" class="dropdown-content">
		<li><a href="${pagesBrand}"><mytaglib:i18n
					key="table.column.Brands" /></a></li>
		<li><a href="${pagesModel}"><mytaglib:i18n
					key="table.column.Models" /></a></li>
		<li class="divider"></li>
		<li><a href="${pagesCar}"><mytaglib:i18n
					key="table.column.Cars" /></a></li>
	</ul>

	<ul id="dropdown2" class="dropdown-content">
		<li><a href="${pagesParking}"><mytaglib:i18n
					key="table.column.Parkings" /></a></li>
		<sec:authorize access="hasAnyRole('admin', 'manager')">
			<li><a href="${pagesPlace}"><mytaglib:i18n
						key="table.column.Places" /></a></li>
		</sec:authorize>
		<li class="divider"></li>

	</ul>

	<nav class="nav-extended">
		<ul class="left hide-on-med-and-down">

			<li><a class="right" class="white-text"
				href="${contextPath}?language=ru">RU</a></li>
			<li><a class="right" class="white-text"
				href="${contextPath}?language=en">EN</a></li>

		</ul>

		<div class="nav-wrapper container">


			<ul class="left hide-on-med-and-down">


				<%-- <li><a href="${contextPath}/">Home</a></li> --%>

				<li><sec:authorize access="hasAnyRole('admin', 'manager')">
						<li><a class="dropdown-trigger" href="#!"
							data-target="dropdown1"><mytaglib:i18n
									key="table.column.Cars" /><i class="material-icons right">arrow_drop_down</i></a></li>
					</sec:authorize></li>

				<li><a class="dropdown-trigger" href="#!"
					data-target="dropdown2"><mytaglib:i18n
							key="table.column.Parkings" /><i class="material-icons right">arrow_drop_down</i></a></li>
				<li><a href="${pagesTariff}"><mytaglib:i18n
							key="table.column.Tariffs" /></a></li>

				<sec:authorize access="hasAnyRole('user', 'manager', 'admin')">

					<li><a href="${pagesPrivate}"><mytaglib:i18n
								key="table.column.Private" /></a></li>
				</sec:authorize>


				<li><sec:authorize access="hasAnyRole('admin', 'manager')">
						<li><a href="${pagesUserAccount}"><mytaglib:i18n
									key="table.column.UserAccounts" /></a></li>
					</sec:authorize> <%-- <li><sec:authorize access="hasAnyRole('admin', 'manager')">
						<li><a href="${pagesFoto}">Photos</a></li>
					</sec:authorize></li> --%>
				<li><sec:authorize access="hasAnyRole('admin', 'manager')">
						<li><a href="${pagesEvent}"><mytaglib:i18n
									key="table.column.Events" /></a></li>
					</sec:authorize></li>

				<li><sec:authorize access="hasAnyRole('admin', 'manager')">
						<a href="${pagesTransaction}"><mytaglib:i18n
								key="table.column.Transactions" /></a>
					</sec:authorize></li>


				<sec:authorize access="!isAnonymous()">
					<sec:authentication property="name" />
					<a class="right" href="${contextPath}/execute_logout"
						title="logout"><i class="material-icons">arrow_forward</i></a>
				</sec:authorize>



			</ul>
			<ul class="right hide-on-med-and-down">

				<sec:authorize access="!isAuthenticated()">
					<li><a href="${contextPath}/login"><mytaglib:i18n
								key="table.column.Login" /></a></li>
					<li><a href="${contextPath}/userAccount/add"><mytaglib:i18n
								key="table.column.Registration" /></a></li>
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