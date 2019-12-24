<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<title>Error Page</title>
<body>
	Request from ${pageContext.errorData.requestURI} is failed

	<br /> Status code: ${pageContext.errorData.statusCode}


	<div align="center">
		<font size="12" color="blue">500: Что-то пошло не так!!!</font>
	</div>
</body>
</html>