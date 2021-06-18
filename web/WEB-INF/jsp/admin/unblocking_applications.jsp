<%@ page pageEncoding="UTF-8" %>
<%@ include file="../../jspf/page.jspf" %>
<%@ include file="../../jspf/taglib.jspf" %>

<html>

<c:set var="title" value="Applications" scope="page"/>
<%@ include file="../../jspf/head.jspf" %>

<body>
<%@ include file="../../jspf/header.jspf" %>
<div class="card my-5">
	<div class="card-body">
		<c:choose>
			<c:when test="${fn:length(apps) == 0}"><fmt:message key="unblocking_applications_jsp.label.noapps"/></c:when>
			<c:otherwise>
				<table class="table table-bordered table-striped">
					<thead class="thead-dark">
					<tr>
						<th scope="col"><fmt:message key="unblocking_applications_jsp.label.id"/></th>
						<th scope="col"><fmt:message key="unblocking_applications_jsp.label.accountName"/></th>
						<th scope="col"><fmt:message key="unblocking_applications_jsp.label.limit"/></th>
						<th scope="col"><fmt:message key="unblocking_applications_jsp.label.currency"/></th>
						<th scope="col"><fmt:message key="unblocking_applications_jsp.label.balance"/></th>
						<th scope="col"><fmt:message key="unblocking_applications_jsp.label.createAccountTime"/></th>
						<th scope="col"><fmt:message key="unblocking_applications_jsp.label.state"/></th>
						<th><fmt:message key="unblocking_applications_jsp.button.unblock"/></th>
						<th></th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="account" items="${apps}">
					<tr>
						<td><p>${account.id}</p></td>
						<td><p>${account.name}</p></td>
						<td><p>${account.limit}</p></td>
						<td><p>${account.currency}</p></td>
						<td><p>${account.balance}</p></td>
						<td><p>${account.createTime}</p></td>
						<td><p>${account.state}</p></td>
						<td>
								<form id="block" action="controller" method="post">
									<input type="hidden" name="command" value="unlock"/>
									<input type="hidden" name="account" value="${account.id}"/>
									<input type="hidden" name="type" value="account"/>
									<input type="hidden" name="page" value="applications"/>
									<p>
										<button type="submit" class="btn btn-success"><fmt:message
												key="unblocking_applications_jsp.button.unblock"/></button>
									</p>
								</form>
						</td>
						</c:forEach>
					</tr>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</div>
</div>
</body>
</html>