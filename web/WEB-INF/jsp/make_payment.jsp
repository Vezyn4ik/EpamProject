<%@ include file="../jspf/page.jspf" %>
<%@ include file="../jspf/taglib.jspf" %>

<html>
<head>
    <c:set var="title" value="make_payment"/>
    <%@ include file="../jspf/head.jspf" %>
</head>
<body>
<%@ include file="../jspf/header.jspf" %>
<div class="wrap content card my-5 card-body">
    <form oninput="commission.value=(amount.value*0.01)" id="register_form" action="controller" method="post">
        <input type="hidden" name="command" value="makePayment"/>
        <fieldset>
            <legend>
                <fmt:message key="make_payment_jsp.label.account"/>
            </legend>
            <select name="account" required>
                <c:forEach var="account" items="${accounts}">
                    <option value="${account.id}">${account.name} ${account.balance} ${account.currency}</option>
                </c:forEach>
                <br/>
            </select>
            <legend>
                <fmt:message key="make_payment_jsp.label.category"/>
            </legend>
            <select name="category" required>
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
                <br/>
            </select>
        </fieldset>
        <br/>
        <fieldset>
            <legend>
                <fmt:message key="make_payment_jsp.label.recipient_account"/>
            </legend>
            <input name="recipient_account" required/><br/>

            <legend>
                <fmt:message key="make_payment_jsp.label.recipient_name"/>
            </legend>
            <input name="recipient_name" required/><br/>
        </fieldset>
        <br/>
        <fieldset>
            <legend>
                <fmt:message key="make_payment_jsp.label.amount"/>
            </legend>
            <input name="amount" type="number" required/><br/>

            <legend>
                <fmt:message key="make_payment_jsp.label.purpose"/>
            </legend>
            <label>
                <input name="purpose" required/>
            </label>
        </fieldset>
        <br/>
        <fieldset>
            <legend>
                <fmt:message key="make_payment_jsp.label.commission"/>
                <output name="commission">0</output>
            </legend>
        </fieldset>
        <br/>
        <div class="button_wrap">
            <button class="btn-lg btn-info" type="submit"><fmt:message key="make_payment_jsp.button.make"/>
            </button>
        </div>
    </form>
</div>
</body>
</html>