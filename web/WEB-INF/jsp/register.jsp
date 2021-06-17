<%@ include file="../jspf/page.jspf" %>
<%@ include file="../jspf/taglib.jspf" %>

<html>
<title>
    Registration
</title>
<link rel="stylesheet" type="text/css" media="screen" href="../../style/register.css"/>
<%@ include file="../jspf/head.jspf" %>

<body>
<div class="wrap">
    <div class="content">
        <div class="card my-5">
            <div class="card-body">
                <form id="register_form" action="controller" method="post">
                    <input type="hidden" name="command" value="register"/>
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
                        <label for="login"><fmt:message key="register_jsp.label.login"/></label>
                        <input class="form-control" id="login" name="login"
                               placeholder="<fmt:message key="register_jsp.placeholder.login"/>"
                               required type="text">
                    </div>
                    <div class="form-group">
                        <label for="password"><fmt:message key="register_jsp.label.password"/></label>
                        <input class="form-control" id="password" name="password"
                               placeholder="<fmt:message key="register_jsp.placeholder.password"/>" required
                               type="password">
                    </div>
                    <div class="form-group">
                        <label for="telephone"><fmt:message key="register_jsp.label.telephone"/></label>
                        <input class="form-control" id="telephone" name="telephone"
                               placeholder="<fmt:message key="register_jsp.placeholder.telephone"/>" required
                               type="tel">
                    </div>
                    <div class="form-group">
                        <label for="email"><fmt:message key="register_jsp.label.email"/></label>
                        <input class="form-control" id="email" name="email"
                               placeholder="<fmt:message key="register_jsp.placeholder.email"/>" type="email">
                    </div>
                    <a href="controller?command=toLogin" style="horiz-align: left" class="btn btn-primary">
                        <fmt:message key="register_jsp.button.login"/>
                    </a>
                    <button class="btn btn-info" style="horiz-align: right" type="submit"><fmt:message key="register_jsp.button.register"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>