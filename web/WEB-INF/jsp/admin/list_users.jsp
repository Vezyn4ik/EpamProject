<%@ page pageEncoding="UTF-8"%>
<%@ include file="../../jspf/page.jspf" %>
<%@ include file="../../jspf/taglib.jspf" %>

<html>

<c:set var="title" value="List users" scope="page" />
<%@ include file="../../jspf/head.jspf" %>

<body>
	<table id="main-container">

		<%@ include file="../../jspf/header.jspf" %>
			
		<tr>
			<td class="content">
			<%-- CONTENT --%>

			<c:choose>
			<c:when test="${fn:length(userList) == 0}">No such users</c:when>
	
			<c:otherwise>
			<table id="list_users_table">
				<thead>
					<tr>
						<td>№</td>
						<td>Name Surname</td>
						<td>Login</td>
						<td>Create time</td>
						<td>State</td>
						<td></td>
					</tr>
				</thead>

				<c:forEach var="user" items="${userList}">
					<tr>
						<td>${user.id}</td>
						<td>${user.name} ${user.surname}</td>
						<td>${user.login}</td>
						<td>${user.createTime}</td>
						<td>${user.state}</td>

						<td><c:if test="${user.state=='LOCKED'}">
							<button >
								<a href="controller?command=unlock&type=user&userId=${user.id}"> Разблокировать</a>
							</button>
						</c:if></td>

						<td><c:if test="${user.state=='UNLOCKED'}">
							<button>
								<a href="controller?command=lock&type=user&userId=${user.id}"> Заблокировать</a>
							</button>
							</c:if>
						</td>
						<td>
							<button>
								<a href="controller?command=userData&userId=${user.id}">Посмотреть данные</a>
							</button>
						</td>
					</tr>
				</c:forEach>			
			</table>
			</c:otherwise>
			</c:choose>
						
			<%-- CONTENT --%>
			</td>
		</tr>
		<%@ include file="../../jspf/footer.jspf" %>
	</table>
</body>
</html>