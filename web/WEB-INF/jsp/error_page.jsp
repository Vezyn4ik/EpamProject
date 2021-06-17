<%@ page isErrorPage="true" %>
<%@ page import="java.io.PrintWriter" %>
<%@ include file="../jspf/page.jspf" %>
<%@ include file="../jspf/taglib.jspf" %>

<html>

<c:set var="title" value="Error" scope="page" />
<%@ include file="../jspf/head.jspf" %>
	
<body>

	<table id="main-container">
		<%@ include file="../jspf/header.jspf"%>
		<tr >
			<td class="content">
				<h2 class="error">
					The following error occurred
				</h2>
				<c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
				<c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>
				<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
				
				<c:if test="${not empty code}">
					<h3>Error code: ${code}</h3>
				</c:if>
				
				<c:if test="${not empty message}">
					<h3>Message: ${message}</h3>
				</c:if>

				<c:if test="${not empty errorMessage and empty exception and empty code}">
					<h3>Error message: ${errorMessage}</h3>
				</c:if>	

				<c:if test="${not empty exception}">
					<hr/>
					<h3>Stack trace:</h3>
					<c:forEach var="stackTraceElement" items="${exception.stackTrace}">
						${stackTraceElement}
					</c:forEach>
				</c:if>	

			</td>
		</tr>
	</table>
</body>
</html>