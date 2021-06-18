<%@ page pageEncoding="UTF-8" %>
<%@ include file="../../jspf/page.jspf" %>
<%@ include file="../../jspf/taglib.jspf" %>

<html>

<c:set var="title" value="user_data" scope="page"/>
<%@ include file="../../jspf/head.jspf" %>
<link rel="stylesheet" type="text/css" media="screen" href="../../style/profile.css"/>
<body>
<div>
    <%@ include file="../../jspf/header.jspf" %>
</div>
<div class="card my-5">
    <div class="card-body">
        <div class="cards">
            <p style="width:100%"><fmt:message key="user_data_jsp.label.login"/>: ${viewedUser.login} </p>
            <p class="title">${viewedUser.name} ${viewedUser.surname}</p>
            <p><fmt:message key="user_data_jsp.label.telephone"/>${viewedUser.telephone}</p>
            <p><fmt:message key="user_data_jsp.label.email"/>${viewedUser.email}</p>
            <p><fmt:message key="user_data_jsp.label.birth"/> ${viewedUser.birth}</p>
            <p><fmt:message key="user_data_jsp.label.createTime"/> ${viewedUser.createTime}</p>

            <c:if test="${viewedUser.state=='LOCKED'}">
                <a class="btn btn-success"
                   href="controller?command=unlock&type=user&userId=${viewedUser.id}&page=user_data"> <fmt:message
                        key="user_data_jsp.button.block"/></a>
            </c:if>
            <c:if test="${viewedUser.state=='UNLOCKED'}">
                <a class="btn btn-danger"
                   href="controller?command=lock&type=user&userId=${viewedUser.id}&page=user_data"> <fmt:message
                        key="user_data_jsp.button.unblock"/></a>
            </c:if>
        </div>
        <c:choose>
            <c:when test="${fn:length(accountList) == 0}">No such accounts</c:when>

            <c:otherwise>
                <table class="table table-bordered table-striped">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col"><fmt:message key="user_data_jsp.label.id"/></th>
                        <th scope="col"><fmt:message key="user_data_jsp.label.accountName"/></th>
                        <th scope="col"><fmt:message key="user_data_jsp.label.createAccountTime"/></th>
                        <th scope="col"><fmt:message key="user_data_jsp.label.limit"/></th>
                        <th scope="col"><fmt:message key="user_data_jsp.label.balance"/></th>
                        <th scope="col"><fmt:message key="user_data_jsp.label.currency"/></th>
                        <th scope="col"><fmt:message key="user_data_jsp.label.state"/></th>
                        <th><fmt:message key="user_data_jsp.button.block"/>/<fmt:message
                                key="user_data_jsp.button.unblock"/></th>
                        <th></th>
                    </tr>
                    </thead>

                    <c:forEach var="account" items="${accountList}">
                        <tr>
                            <td>${account.id}</td>
                            <td>${account.name}</td>
                            <td>${account.createTime}</td>
                            <td>${account.limit}</td>
                            <td>${account.currency}</td>
                            <td>${account.balance}</td>
                            <td>${account.state}</td>
                            <td><c:if test="${account.state=='LOCKED'|| account.state=='WAITING'}">
                                <a class="btn btn-success"
                                   href="controller?command=unlock&type=account&account=${account.id}&userId=${viewedUser.id}&page=user_data">
                                    <fmt:message
                                            key="user_data_jsp.button.unblock"/></a>
                            </c:if>

                                <c:if test="${account.state=='UNLOCKED'}">

                                    <a class="btn btn-danger"
                                       href="controller?command=lock&type=account&account=${account.id}&userId=${viewedUser.id}&page=user_data">
                                        <fmt:message
                                                key="user_data_jsp.button.block"/></a>
                                </c:if>
                            </td>
                            <td>
                                <a class="btn btn-info"
                                   href="controller?command=payments&accountId=${account.id}&sort=numberUp">
                                    <fmt:message
                                            key="user_data_jsp.button.paymentHistory"/></a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>