<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"
	scope="request" />
<c:set var="pagesBrand" value="${contextPath}/brand" scope="request" />
<c:set var="pagesModel" value="${contextPath}/model" scope="request" />
<c:set var="pagesCar" value="${contextPath}/car" scope="request" />
<c:set var="pagesClient" value="${contextPath}/client" scope="request" />
<c:set var="pagesTariff" value="${contextPath}/tariff" scope="request" />
<c:set var="pagesUserAccount" value="${contextPath}/userAccount"
	scope="request" />
<c:set var="pagesFoto" value="${contextPath}/foto" scope="request" />
<c:set var="pagesParking" value="${contextPath}/parking" scope="request" />
<c:set var="pagesPlace" value="${contextPath}/place" scope="request" />
<c:set var="pagesPlaceOwner" value="${contextPath}/placeOwner"
	scope="request" />
<c:set var="pagesEvent" value="${contextPath}/event" scope="request" />
<c:set var="pagesTransaction" value="${contextPath}/transaction"
	scope="request" />



<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><tiles:insertAttribute name="title" /></title>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/css/materialize.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>

<link rel="stylesheet" href="${contextPath}/resources/css/custom.css">
<script src="${contextPath}/resources/js/init-materialize-forms.js"></script>
<script src="${contextPath}/resources/js/init-menu.js"></script>
</head>
<body>
	<tiles:insertAttribute name="header" />
	<main>
	<div class="container">
		<tiles:insertAttribute name="body" />


	</div>

	</main>
	<tiles:insertAttribute name="footer" />
</body>
</html>