<%@ page pageEncoding="UTF-8" %>
<%@ include file="../../jspf/page.jspf" %>
<%@ include file="../../jspf/taglib.jspf" %>

<html>

<c:set var="title" value="List orders" scope="page"/>
<%@ include file="../../jspf/head.jspf" %>

<body>
<div>
    <%@ include file="../../jspf/header.jspf" %>
</div>
<div class="card my-5">
    <div class="card-body">
            <div class="cards">
                <p style="width:100%"><fmt:message key="register_jsp.label.login"/>: ${viewedUser.login} </p>
                <p class="title">${viewedUser.name} ${viewedUser.surname}</p>
                <p><fmt:message key="register_jsp.label.birth"/> ${viewedUser.birth}</p>

            </div>
            <<c:if test="${viewedUser.state=='LOCKED'}">
            <button  class="btn btn-success">
                <a href="controller?command=unlock&type=user&userId=${viewedUser.id}"> Разблокировать</a>
            </button>
        </c:if>
            <c:if test="${viewedUser.state=='UNLOCKED'}">
                <button class="btn btn-danger">
                    <a href="controller?command=lock&type=user&userId=${viewedUser.id}"> Заблокировать</a>
                </button>
            </c:if>
            <c:choose>
                <c:when test="${fn:length(accountList) == 0}">No such accounts</c:when>

                <c:otherwise>
                    <table class="table table-bordered table-striped">
                        <thead class="thead-dark">
                        <tr>
                            <th>name</th>
                            <th>create time</th>
                            <th>limit</th>
                            <th>currency</th>
                            <th>balance</th>
                            <th>state</th>
                        </tr>
                        </thead>

                        <c:forEach var="account" items="${accountList}">
                            <tr>
                                <td>${account.name}</td>
                                <td>${account.createTime}</td>
                                <td>${account.limit}</td>
                                <td>${account.currency}</td>
                                <td>${account.balance}</td>
                                <td>${account.state}</td>
                                <td><c:if test="${account.state=='LOCKED'}">
                                    <button>
                                        <a href="controller?command=unlock&type=account&account=${account.id}">
                                            Разблокировать</a>
                                    </button>
                                </c:if></td>

                                <td><c:if test="${account.state=='UNLOCKED'}">
                                    <button>
                                        <a href="controller?command=lock&type=account&account=${account.id}"> Заблокировать</a>
                                    </button>
                                </c:if>
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