<%@ include file="../jspf/page.jspf" %>
<%@ include file="../jspf/taglib.jspf" %>

<html>
<head>
    <title>
        Profile
    </title>
    <link rel="stylesheet" type="text/css" media="screen" href="../../style/profile.css"/>
    <link rel="stylesheet" type="text/css" media="screen" href="../../style/header.css"/>
    <script type="text/javascript">
        function func() {
            if (document.getElementById("settings_form").style.display === "none") {
                document.getElementById("settings_form").style.display = "block";
                document.getElementById("changeVisibility").textContent = '<fmt:message
                        key="profile_jsp.button.hide"/>';
            } else {
                document.getElementById("settings_form").style.display = "none";
                document.getElementById("changeVisibility").textContent = '<fmt:message
                        key="profile_jsp.button.editProfile"/>';
            }
        }
    </script>
    <%@ include file="../jspf/head.jspf" %>
</head>
<body>
<div>
    <%@ include file="../jspf/header.jspf" %>
</div>
<div class="wrap">
    <div class="content">

        <div class="cards">
            <p style="width:100%"><fmt:message key="profile_jsp.label.login"/>: ${user.login} </p>
            <h1> ${user.name} ${user.surname}</h1>
            <c:if test="${not empty user.email}"><p class="title"><fmt:message
                    key="profile_jsp.label.email"/>: ${{user.email}}</p></c:if>
            <p class="title"><fmt:message key="profile_jsp.label.telephone"/>: ${{user.telephone}}</p>
            <p><fmt:message key="profile_jsp.label.birth"/> ${user.birth}</p>
            <p><fmt:message key="profile_jsp.label.createTime"/> ${user.createTime}</p>
            <p>
                <button id="changeVisibility" onclick="func()">
                    <fmt:message
                            key="profile_jsp.button.editProfile"/></button>
            </p>
        </div>
        <form style="display:none;" id="settings_form" action="controller" method="post">
            <input type="hidden" name="command" value="updateProfile"/>
            <div>
                <p>
                    <fmt:message key="profile_jsp.label.localization"/>
                </p>
                <select name="localeToSet">
                    <c:choose>
                        <c:when test="${not empty defaultLocale}">
                            <option value="">${defaultLocale}[Default]</option>
                        </c:when>
                        <c:otherwise>
                            <option value=""/>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach var="localeName" items="${locales}">
                        <option value="${localeName}">${localeName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="name"> <fmt:message key="register_jsp.label.name"/> </label>
                <input class="form-control" name="name" id="name"
                       placeholder="<fmt:message key="register_jsp.placeholder.name"/>"
                       pattern="[A-Za-zР-пр-џ]+" required/>
            </div>
            <div class="form-group">
                <label for="surname"><fmt:message key="register_jsp.label.surname"/></label>
                <input id="surname" class="form-control" name="surname"
                       placeholder="<fmt:message key="register_jsp.placeholder.surname"/>"
                       pattern="[A-Za-zР-пр-џ]+" required/>
            </div>
            <div class="form-group">
                <label for="birth"> <fmt:message key="register_jsp.label.birth"/> </label>
                <input id="birth" class="form-control" name="birth"
                       placeholder="<fmt:message key="register_jsp.placeholder.birth"/>"
                       type="date" max="2005-01-01" min="1900-01-01" required/>
            </div>
            <div class="form-group">
                <label for="password"><fmt:message key="register_jsp.label.password"/></label>
                <input class="form-control" id="password" name="password"
                       placeholder="<fmt:message key="register_jsp.placeholder.password"/>" required
                       type="password">
            </div>
            <div class="form-group">
                <label for="email"><fmt:message key="register_jsp.label.email"/></label>
                <input class="form-control" id="email" name="email"
                       placeholder="<fmt:message key="register_jsp.placeholder.email"/>" type="email">
            </div>
            <div class="button_wrap">
                <button class="btn btn-lg btn-info" type="submit"><fmt:message
                        key="profile_jsp.button.update"/>
                </button>
            </div>
        </form>
    </div>
</div>
<div class="clear"></div>
<div class="wrapper">
    <form id="addAccount" action="controller" method="post">
        <input type="hidden" name="command" value="addAccount"/>
        <div class="form-group">
            <label for="nameAccount"> <fmt:message key="profile_jsp.label.accountName"/> </label>
            <input class="form-control" name="name" id="nameAccount"
                   required/>
        </div>
        <div class="form-group">
            <label for="limit"> <fmt:message key="profile_jsp.label.limit"/> </label>
            <input class="form-control" name="limit" id="limit"
                   type="number" min="0" required/>
        </div>
        <div class="form-group">
            <label for="currency"> <fmt:message key="profile_jsp.label.currency"/> </label>
            <input class="form-control" name="currency" id="currency"
                   type="text" maxlength="3" required/>
        </div>
        <div class="button_wrap">
            <button class="btn-lg btn-info" type="submit"><fmt:message
                    key="profile_jsp.button.addAccount"/>
            </button>
        </div>
    </form>
</div>
<div class="card my-5">
    <div class="card-body">
        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
            <tr>
                <th scope="col"><fmt:message key="profile_jsp.label.accountName"/></th>
                <th scope="col"><fmt:message key="profile_jsp.label.createAccountTime"/></th>
                <th scope="col"><fmt:message key="profile_jsp.label.limit"/></th>
                <th scope="col"><fmt:message key="profile_jsp.label.balance"/></th>
                <th scope="col"><fmt:message key="profile_jsp.label.currency"/></th>
                <th scope="col"><fmt:message key="profile_jsp.label.state"/></th>
                <th><fmt:message key="profile_jsp.button.block"/>/<fmt:message
                        key="profile_jsp.button.unblock"/></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="account" items="${accounts}">
            <tr>
                <td><p>${account.name}</p></td>
                <td><p>${account.createTime}</p></td>
                <td><p>${account.limit}</p></td>
                <td><p>${account.balance}</p></td>
                <td><p>${account.currency}</p></td>
                <td><p>${account.state}</p></td>

                <td><c:if test="${account.state=='UNLOCKED'}">
                    <form id="block" action="controller" method="post">
                        <input type="hidden" name="command" value="lock"/>
                        <input type="hidden" name="account" value="${account.id}"/>
                        <input type="hidden" name="type" value="account"/>
                        <p>
                            <button type="submit" class="btn btn-danger"><fmt:message
                                    key="profile_jsp.button.block"/></button>
                        </p>
                    </form>
                </c:if>
                    <c:if test="${account.state=='LOCKED'}">
                        <form id="block" action="controller" method="post">
                            <input type="hidden" name="command" value="unlock"/>
                            <input type="hidden" name="account" value="${account.id}"/>
                            <input type="hidden" name="type" value="account"/>
                            <p>
                                <button type="submit" class="btn btn-success"><fmt:message
                                        key="profile_jsp.button.unblock"/></button>
                            </p>
                        </form>
                    </c:if>
                </td>
                <td>
                    <form>
                        <input type="hidden" name="command" value="payments">
                        <input type="hidden" name="accountId" value="${account.id}">
                        <button type="submit" class="btn btn-warning"><fmt:message
                                key="profile_jsp.button.paymentHistory"/></button>
                    </form>
                </td>
                </c:forEach>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</div>
<%@ include file="../jspf/footer.jspf" %>
</body>
</html>