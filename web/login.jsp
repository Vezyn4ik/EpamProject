<%@ include file="./WEB-INF/jspf/page.jspf" %>
<%@ include file="./WEB-INF/jspf/taglib.jspf" %>

<html>
<head>
    <title>
        Login
    </title>
    <link rel="stylesheet" type="text/css" media="screen" href="style/login.css"/>
	<%@ include file="./WEB-INF/jspf/head.jspf" %>
</head>
<body>

<div class="wrapper">
<div class="container">
        <form id="login_form" action="controller" method="post" class="form-signin">
            <input type="hidden" name="command" value="login"/>
            <h3 class="form-signin-heading"><fmt:message key="login_jsp.label.welcome"/></h3>
            <hr class="colorgraph">
            <br>
            <input type="text" class="form-control" name="login"
                   placeholder="<fmt:message key="login_jsp.input.login"/>" required autofocus/>
            <input type="password" class="form-control" name="password"
                   placeholder="<fmt:message key="login_jsp.input.password"/>" required/>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message
                    key="login_jsp.button.login"/></button>
            <a href="controller?command=toRegister" class="btn btn-lg btn-primary btn-block">
                <fmt:message key="login_jsp.button.register"/>
            </a>
            <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger"> ${errorMessage} </div>
            </c:if>
        </form>
    </div>
</div>
</body>
</html>