<%@ page pageEncoding="UTF-8" %>
<%@ include file="../../jspf/page.jspf" %>
<%@ include file="../../jspf/taglib.jspf" %>

<html>

<c:set var="title" value="List users" scope="page"/>
<%@ include file="../../jspf/head.jspf" %>

<body>
<%@ include file="../../jspf/header.jspf" %>
<div class="card my-5">
    <div class="card-body">
        <c:choose>
            <c:when test="${fn:length(userList) == 0}">No such users</c:when>
            <c:otherwise>
                <table class="table table-bordered table-striped">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col"><fmt:message key="list_users_jsp.label.id"/></th>
                        <th scope="col"><fmt:message key="list_users_jsp.label.name"/></th>
                        <th scope="col"><fmt:message key="list_users_jsp.label.login"/></th>
                        <th scope="col"><fmt:message key="list_users_jsp.label.createTime"/></th>
                        <th scope="col"><fmt:message key="list_users_jsp.label.state"/></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${userList}">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name} ${user.surname}</td>
                            <td>${user.login}</td>
                            <td>${user.createTime}</td>
                            <td>${user.state}</td>
                            <td><c:if test="${user.state=='LOCKED'}">
                                    <a class="btn btn-success"  href="controller?command=unlock&type=user&userId=${user.id}">
                                        <fmt:message key="list_users_jsp.button.unlock"/></a>
                            </c:if></td>
                            <td><c:if test="${user.state=='UNLOCKED'}">
                                    <a class="btn btn-danger"  href="controller?command=lock&type=user&userId=${user.id}">
                                        <fmt:message key="list_users_jsp.button.lock"/></a>
                            </c:if>
                            </td>
                            <td>
                                    <a class="btn btn-primary" href="controller?command=userData&userId=${user.id}"><fmt:message key="list_users_jsp.button.details"/></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>