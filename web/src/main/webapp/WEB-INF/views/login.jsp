<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<h2>
	<mytaglib:i18n key="table.column.Login" />
	<mytaglib:i18n key="table.column.with" />
	Email
	<mytaglib:i18n key="table.column.and" />
	<mytaglib:i18n key="table.column.Password" />
</h2>
<div class="row">
	<div class="col s3"></div>
	<div class="col s6">
		<form name='loginForm' action="<c:url value='login' />" method='POST'>
			<div class="row">
				<div class="input-field col s12 center">
					<input type='text' name='username' value=''> <label
						for="username">Email:</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<input type='password' name='password' /><label for="password"><mytaglib:i18n
							key="table.column.Password" />:</label>
				</div>
			</div>
			<c:if test="${not empty error}">
				<div class="row">
					<div class="col s12 center">
						<div class="error">${error}</div>
					</div>
				</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="row">
					<div class="col s12 center">
						<div class="msg">${msg}</div>
					</div>
				</div>
			</c:if>
			<div class="row">
				<div class="col s12 center">
					<button class="btn waves-effect waves-light " type="submit"><mytaglib:i18n
							key="table.column.Enter" /></button>
				</div>
			</div>
		</form>
	</div>
	<div class="col s3"></div>
</div>