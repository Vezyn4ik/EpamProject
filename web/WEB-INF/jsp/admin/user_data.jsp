<%@ page pageEncoding="UTF-8" %>
<%@ include file="../../jspf/page.jspf" %>
<%@ include file="../../jspf/taglib.jspf" %>

<html>

<c:set var="title" value="List orders" scope="page"/>
<%@ include file="../../jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="../../jspf/header.jspf" %>

    <tr>
        <%-- CONTENT --%>
        <td class="content">
            <div class="cards">
                <p style="width:100%"><fmt:message key="register_jsp.label.login"/>: ${viewedUser.login} </p>
                <p class="title">${viewedUser.name} ${viewedUser.surname}</p>
                <p><fmt:message key="register_jsp.label.birth"/> ${viewedUser.birth}</p>
                <p><fmt:message key="account_jsp.label.registerTime"/> ${viewedUser.createTime}</p>
            </div>
            <<c:if test="${viewedUser.state=='LOCKED'}">
            <button>
                <a href="controller?command=unlock&type=user&userId=${viewedUser.id}"> Разблокировать</a>
            </button>
        </c:if>

            <c:if test="${viewedUser.state=='UNLOCKED'}">
                <button>
                    <a href="controller?command=lock&type=user&userId=${viewedUser.id}"> Заблокировать</a>
                </button>
            </c:if>
        </td>
        <td>
            <c:choose>
                <c:when test="${fn:length(accountList) == 0}">No such accounts</c:when>

                <c:otherwise>
                    <table id="list_account_table">
                        <thead>
                        <tr>
                            <td>name</td>
                            <td>create time</td>
                            <td>limit</td>
                            <td>currency</td>
                            <td>balance</td>
                            <td>state</td>
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

            <%-- CONTENT --%>
        </td>
    </tr>
    <%@ include file="../../jspf/footer.jspf" %>
</table>
</body>
</html>